package com.example.carriapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carriapp.Config.DataConverter;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;
import com.example.carriapp.ui.MapasActivity;

import java.util.List;

public class VerCarribarActivity extends AppCompatActivity {

    AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carribar);

        TextView textViewNombreCarribar = (TextView) findViewById(R.id.textViewNombre);

        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, "prueba2")
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

    public void inicializarComponentes(){

        List<Carribar> listaCarribares = db.carribarDao().getAllCarribares();
        Carribar carribarAMostrar = listaCarribares.get(0);

        ((ImageView) findViewById(R.id.imagenCarribar)).setImageBitmap(DataConverter.convertByteArrayToImgae(carribarAMostrar.getImagen()));

        ((TextView) findViewById(R.id.textViewNombre)).setText(carribarAMostrar.getNombre());
        ((TextView) findViewById(R.id.textViewDireccion)).setText(carribarAMostrar.getDireccion());
        ((TextView) findViewById(R.id.textViewDistancia)).setText("Calcular Distancia");

        //Calcular hora si esta abierto o cerrado y cambiar visibilidad
        ((TextView) findViewById(R.id.textViewHoraCierre)).setText(carribarAMostrar.getHoraCierre());
        ((TextView) findViewById(R.id.textViewHoraApertura)).setText(carribarAMostrar.getHoraApertura());

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
}