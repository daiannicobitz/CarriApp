package com.example.carriapp;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.carriapp.Config.Constantes;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.CarribarView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button botonAgregar;
    Button botonVer;
    AppDataBase db;
    RecyclerView listaCarribar;
    List<CarribarView> listaCarribares;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, Constantes.BD_NAME)
                .allowMainThreadQueries()
                .build();

        botonAgregar = (Button) findViewById(R.id.buttonAgregar);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), AgregarCarribarActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        botonVer = (Button) findViewById(R.id.button2Ver);
        botonVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ListaCarribaresActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }


}