package com.example.carriapp;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carriapp.Config.Constantes;
import com.example.carriapp.Config.DataConverter;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;
import com.example.carriapp.Entidades.CarribarView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;

public class VerCarribarActivity extends AppCompatActivity {

    AppDataBase db;
    Button botonLlevame, botonVolver;
    TextView nombreTextView;
    TextView direccionTextView;
    TextView distanciaTextView;
    TextView abiertoTextView;
    TextView cerradoTextView;
    TextView horaCierreTextView;
    TextView horaAperturaTextView;
    TextView contactoTextView;
    TextView hamburguesasTextView;
    TextView panchosTextView;
    TextView choripanesTextView;
    TextView milanesaTextView;
    TextView pizzaTextView;
    TextView bondiolaTextView;
    TextView papasFritasTextView;
    ImageView carriImagenView;
    Bitmap imagenBitmap;
    FusedLocationProviderClient fusedLocationClient;
    Double latitudUbicacionActual;
    Double longitudUbicacionActual;
    Float distancia;
    Location locationActual;
    Location locationCarri;
    DrawerLayout drawerLayout;
    TextView textToolBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carribar);

        drawerLayout = findViewById(R.id.drawer_layout_ver_unico_carribar);

        textToolBar = findViewById(R.id.textToolBar);
        textToolBar.setText("Carribar");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        TextView textViewNombreCarribar = (TextView) findViewById(R.id.textViewNombre);

        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, Constantes.BD_NAME)
                .allowMainThreadQueries()
                .build();


        inicializarComponentes();
        botonLlevame = (Button) findViewById(R.id.buttonLlevame);
        botonLlevame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MapasActivity.class);
                intent.putExtra("LatitudCarri", locationCarri.getLatitude());
                intent.putExtra("LongitudCarri", locationCarri.getLongitude());
//                intent.putExtra("LatitudActual", locationActual.getLatitude());
//                intent.putExtra("LongitudActual", locationActual.getLongitude());
                startActivity(intent);
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

    public void ClickMenu(View view) {
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void ClickAddCarribar(View view) { MainActivity.redirectActivity(this, AgregarCarribarActivity.class); }

    public void ClickVerCarribar(View view) { MainActivity.redirectActivity(this, ListaCarribaresActivity.class); }

    public void ClickSalir(View view) {
        MainActivity.salir(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void inicializarComponentes(){

        CarribarView carribar = (CarribarView) getIntent().getSerializableExtra("CarribarView");
        Carribar item = db.carribarDao().getCarribarById(carribar.getIdCarribar());

        nombreTextView = findViewById(R.id.textViewNombre);
        direccionTextView = findViewById(R.id.textViewDireccion);
        distanciaTextView = findViewById(R.id.textViewDistancia);
        abiertoTextView = findViewById(R.id.textViewAbierto);
        cerradoTextView = findViewById(R.id.textViewCerrado);
        horaCierreTextView = findViewById(R.id.textViewHoraCierre);
        horaAperturaTextView = findViewById(R.id.textViewHoraApertura);
        contactoTextView = findViewById(R.id.textViewContacto);
        hamburguesasTextView = findViewById(R.id.textViewHamburguesas);
        panchosTextView = findViewById(R.id.textViewPanchos);
        choripanesTextView = findViewById(R.id.textViewChoripanes);
        milanesaTextView = findViewById(R.id.textViewMilanesa);
        pizzaTextView = findViewById(R.id.textViewPizza);
        bondiolaTextView = findViewById(R.id.textViewBondiola);
        papasFritasTextView = findViewById(R.id.textViewPapasFritas);
        carriImagenView = findViewById(R.id.imagenCarribar);

        imagenBitmap= DataConverter.convertByteArrayToImgae(item.getImagen());
        carriImagenView.setImageBitmap(imagenBitmap);

        nombreTextView.setText(item.getNombre());
        direccionTextView.setText(item.getDireccion());

        obtenerDistancia(item.getLatitud(), item.getLongitud());

        if(calcularAperturaCierre(item.getHoraCierre(), item.getHoraApertura())){
            abiertoTextView.setVisibility(View.VISIBLE);
            cerradoTextView.setVisibility(View.GONE);
            horaCierreTextView.setText("Hora Cierre:" + item.getHoraCierre());
            horaAperturaTextView.setVisibility(View.GONE);
            horaCierreTextView.setVisibility(View.VISIBLE);
        }else{
            abiertoTextView.setVisibility(View.GONE);
            cerradoTextView.setVisibility(View.VISIBLE);
            horaCierreTextView.setVisibility(View.GONE);
            horaAperturaTextView.setText("Hora apertura: " + item.getHoraApertura());
            horaAperturaTextView.setVisibility(View.VISIBLE);
        }

        contactoTextView.setText(item.getContacto());

        if(!item.getHayHamburguesa())
            hamburguesasTextView.setVisibility(View.GONE);

        if(!item.getHayBondiola())
            bondiolaTextView.setVisibility(View.GONE);

        if(!item.getHayChoripan())
            choripanesTextView.setVisibility(View.GONE);

        if(!item.getHayMilanesa())
            milanesaTextView.setVisibility(View.GONE);

        if(!item.getHayPancho())
            panchosTextView.setVisibility(View.GONE);

        if(!item.getHayPapasFritas())
            papasFritasTextView.setVisibility(View.GONE);

        if(!item.getHayPizza())
            pizzaTextView.setVisibility(View.GONE);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean calcularAperturaCierre(String horarioCierre, String horarioApertura){

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));

        LocalTime horaCierre = LocalTime.parse( horarioCierre); //00:50
        LocalTime horaApertura = LocalTime.parse( horarioApertura); //22:14
        LocalTime horaActual = LocalTime.parse(df.format(date)); //1 si horaApertura > horaCierre

        return horaActual.compareTo(horaApertura) == 1 && horaCierre.compareTo(horaActual) == 1;
    }

    public void obtenerDistancia(String latitudCarri, String longitudCarri){

        Double lat;
        Double lon;

        lat = Double.parseDouble(latitudCarri);
        lon = Double.parseDouble(longitudCarri);

        locationCarri = new Location("Carribar");
        locationCarri.setLatitude(lat);  //latitud
        locationCarri.setLongitude(lon); //longitud

        getLocation(locationCarri);


    }

    private void getLocation(Location l){
        @SuppressLint("MissingPermission")
        Task<Location> locationTask = fusedLocationClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    latitudUbicacionActual = location.getLatitude();
                    longitudUbicacionActual = location.getLongitude();

                    locationActual = new Location("Localizacion actual");
                    locationActual.setLatitude(latitudUbicacionActual);  //latitud
                    locationActual.setLongitude(longitudUbicacionActual);

                    distancia = l.distanceTo(locationActual);

                    ((TextView) findViewById(R.id.textViewDistancia)).setText("Distancia a carribar: " + Float.toString(distancia) + " metros");

                }
            }
        });
    }
}