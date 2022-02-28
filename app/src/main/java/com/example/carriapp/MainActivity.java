package com.example.carriapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;
import com.example.carriapp.Entidades.CarribarView;

import java.util.ArrayList;
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
        setContentView(R.layout.activity_recyclerview);

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
                Intent intent = new Intent (v.getContext(), VerCarribarActivity.class);
                startActivityForResult(intent, 0);
            }
        });



    }
        /*Carribar carriPrueba = new Carribar("prueba2","AA 7013","20","23",
                "3434474355", true, true, false, false, false,
                false, true);

        db.carribarDao().insert(carriPrueba);*/

        listaCarribar = findViewById(R.id.listRecyclerView);
        listaCarribar.setLayoutManager(new LinearLayoutManager(this));

        listaCarribares = new ArrayList<>();
        listaCarribares = db.carribarDao().getAllCarribaresView();
        System.out.println(listaCarribares.get(0).toString());

        ListAdapter listaCarribaresAdapter = new ListAdapter(listaCarribares, this);
        listaCarribar.setAdapter(listaCarribaresAdapter);



    public void mapas(){

    }

}