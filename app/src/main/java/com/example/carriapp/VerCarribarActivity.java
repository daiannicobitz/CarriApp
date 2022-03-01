package com.example.carriapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carriapp.Config.Constantes;
import com.example.carriapp.Config.DataConverter;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;
import com.example.carriapp.Entidades.CarribarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;

public class VerCarribarActivity extends AppCompatActivity {

    AppDataBase db;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carribar);

        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, Constantes.BD_NAME)
                .allowMainThreadQueries()
                .build();

        inicializarComponentes();

        Button botonLlevame = (Button) findViewById(R.id.buttonLlevame);
        botonLlevame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MapasActivity.class);
                startActivityForResult(intent, 0);
            }
        });


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
        //distanciaTextView.setText(item.getDistancia);

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

        //Devuelve 0 si esta cerrado, 1 si esta abierto

//        int horaCierre = Integer.parseInt(horarioCierre.substring(0,2));
//        int minutoCierre = Integer.parseInt(horarioCierre.substring(3));
//        int horaApertura = Integer.parseInt(horarioApertura.substring(0,2));
//        int minutoApertura = Integer.parseInt(horarioApertura.substring(3));
//        LocalTime time = LocalTime.now();
//        int horaActual = time.getHour();
//        int minutoActual = time.getMinute();
//
//        if(horaCierre == 0) horaCierre +=24;
//        if(horaActual == 0) horaActual +=24;
//
//        System.out.println("hora consultada"+horaCierre);
//        System.out.println("hora actual"+horaActual);
//        System.out.println("minuto consultado"+minutoCierre);
//
//        if(horaCierre>horaActual && horaApertura <= horaActual) {
//            if(horaApertura==horaActual)
//                if(minutoApertura>minutoActual) return 0;
//            return 1;
//        }
//        else if(horaCierre<horaActual || horaApertura>horaActual) return 0;
//        else if(horaCierre==horaActual)
//            if(minutoCierre>minutoActual) return 1;
//            else if(minutoCierre<minutoActual)return 0;
//            else if(minutoCierre==minutoActual) return 0;

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));

        LocalTime horaCierre = LocalTime.parse( horarioCierre); //00:50
        LocalTime horaApertura = LocalTime.parse( horarioApertura); //22:14
        LocalTime horaActual = LocalTime.parse(df.format(date)); //1 si horaApertura > horaCierre

        return horaActual.compareTo(horaApertura) == 1 && horaCierre.compareTo(horaActual) == 1;
    }
}