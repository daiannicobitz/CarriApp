package com.example.carriapp;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carriapp.Config.DataConverter;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class VerCarribarActivity extends AppCompatActivity {

    AppDataBase db;
    Button botonLlevame, botonVolver;
    FusedLocationProviderClient fusedLocationClient;
    Double latitudUbicacionActual;
    Double longitudUbicacionActual;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carribar);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



        TextView textViewNombreCarribar = (TextView) findViewById(R.id.textViewNombre);

        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, "Constantes.BD_NAME")
                .allowMainThreadQueries()
                .build();

        getLocation();
        inicializarComponentes();
        botonLlevame = (Button) findViewById(R.id.buttonLlevame);
        botonLlevame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MapasActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        botonVolver = (Button) findViewById(R.id.buttonVolver);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ListaCarribaresActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void inicializarComponentes(){

        List<Carribar> listaCarribares = db.carribarDao().getAllCarribares();
        Carribar carribarAMostrar = listaCarribares.get(0);

        ((ImageView) findViewById(R.id.imagenCarribar)).setImageBitmap(DataConverter.convertByteArrayToImgae(carribarAMostrar.getImagen()));

        ((TextView) findViewById(R.id.textViewNombre)).setText(carribarAMostrar.getNombre());
        ((TextView) findViewById(R.id.textViewDireccion)).setText(carribarAMostrar.getDireccion());


        ((TextView) findViewById(R.id.textViewDistancia)).setText(obtenerDistancia(carribarAMostrar.getLatitud(), carribarAMostrar.getLongitud()));

        TextView horaCarribarAbierto = (TextView) findViewById(R.id.textViewHoraApertura);
        TextView horaCarribarCerrado = (TextView) findViewById(R.id.textViewHoraCierre);
        TextView carribarAbierto = (TextView) findViewById(R.id.textViewAbierto);
        TextView carribarCerrado = (TextView) findViewById(R.id.textViewCerrado);

        horaCarribarCerrado.setText("Cierra: " + carribarAMostrar.getHoraCierre());
        horaCarribarAbierto.setText("Abre: " + carribarAMostrar.getHoraApertura());

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));

        LocalTime horaCierre = LocalTime.parse( carribarAMostrar.getHoraCierre()); //00:50
        LocalTime horaApertura = LocalTime.parse( carribarAMostrar.getHoraApertura()); //22:14
        LocalTime horaActual = LocalTime.parse(df.format(date)); //1 si horaApertura > horaCierre

        if(horaActual.compareTo(horaApertura) == 1 && horaCierre.compareTo(horaActual) == 1){
            System.out.println("ABIERTOOO");
            carribarAbierto.setVisibility(View.VISIBLE);
            carribarCerrado.setVisibility(View.GONE);
            horaCarribarAbierto.setVisibility(View.GONE);
            horaCarribarCerrado.setVisibility(View.VISIBLE);
        }else{
            System.out.println("CERRADO");
            carribarAbierto.setVisibility(View.GONE);
            carribarCerrado.setVisibility(View.VISIBLE);
            horaCarribarAbierto.setVisibility(View.VISIBLE);
            horaCarribarCerrado.setVisibility(View.GONE);
        }

        System.out.println(horaApertura.compareTo(horaCierre));

        ((TextView) findViewById(R.id.textViewContacto)).setText(carribarAMostrar.getContacto());

        if(!carribarAMostrar.getHayHamburguesa())
            ((TextView) findViewById(R.id.textViewHamburguesas)).setVisibility(View.GONE);

        if(!carribarAMostrar.getHayBondiola())
            ((TextView) findViewById(R.id.textViewBondiola)).setVisibility(View.GONE);

        if(!carribarAMostrar.getHayChoripan())
            ((TextView) findViewById(R.id.textViewChoripanes)).setVisibility(View.GONE);

        if(!carribarAMostrar.getHayMilanesa())
            ((TextView) findViewById(R.id.textViewMilanesa)).setVisibility(View.GONE);

        if(!carribarAMostrar.getHayPancho())
            ((TextView) findViewById(R.id.textViewPanchos)).setVisibility(View.GONE);

        if(!carribarAMostrar.getHayPapasFritas())
            ((TextView) findViewById(R.id.textViewPapasFritas)).setVisibility(View.GONE);

        if(!carribarAMostrar.getHayPizza())
            ((TextView) findViewById(R.id.textViewPizza)).setVisibility(View.GONE);
    }

    public String obtenerDistancia(String latitudCarri, String longitudCarri){

        Double lat;
        Double lon;

        System.out.println("QQQQQ" + latitudUbicacionActual);
        System.out.println(longitudUbicacionActual);

        lat = Double.parseDouble(latitudCarri);
        lon = Double.parseDouble(longitudCarri);

        Location locationCarri = new Location("Carribar");
        locationCarri.setLatitude(lat);  //latitud
        locationCarri.setLongitude(lon); //longitud

//        Location locationActual = new Location("Localizacion actual");
//        locationActual.setLatitude(latActual);  //latitud
//        locationActual.setLongitude(lonActual); //longitud
//
//        double distance = locationCarri.distanceTo(locationActual);
//
//        String str = Double.toString(distance);
        return  "str" ;
    }

    private void getLocation(){
        @SuppressLint("MissingPermission")
        Task<Location> locationTask = fusedLocationClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    latitudUbicacionActual = location.getLatitude();
                    longitudUbicacionActual = location.getLongitude();
                    System.out.println("QQQQQ" + latitudUbicacionActual);
                    System.out.println(longitudUbicacionActual);
                }
            }
        });
    }
}