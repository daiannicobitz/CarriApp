package com.example.carriapp;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;
import com.example.carriapp.Entidades.CarribarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDataBase db;
    RecyclerView listaCarribar;
    List<CarribarView> listaCarribares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class,"produccion")
                .allowMainThreadQueries()
                .build();

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



        System.out.println(listaCarribares.get(0).getDireccion());

    }


    /*public List<ListElement> crearListaElementos(List<com.example.carriapp.Entidades.CarribarView> lista){
        ArrayList<ListElement> listaRetorno= new ArrayList<>();

        for(int i=0; i<lista.size();i++){
            ListElement elemento = new ListElement(lista.get(i));
            listaRetorno.add(elemento);
        }
    }*/
}