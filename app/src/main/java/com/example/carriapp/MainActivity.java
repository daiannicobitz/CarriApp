package com.example.carriapp;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,"produccion")
                .allowMainThreadQueries()
                .build();

        com.example.carriapp.Entidades.Carribar carriPrueba = new com.example.carriapp.Entidades.Carribar("prueba2","AA 7013","20","23",
                "3434474355", true, true, false, false, false,
                false, true);

        db.carribarDao().insert(carriPrueba);

        System.out.println("ACA LLEGÓ");

        System.out.println(db.carribarDao().count());

        List<Carribar> listaCarribares = db.carribarDao().getAllCarribares();

        System.out.println(listaCarribares.get(0).getDireccion());
    }
}