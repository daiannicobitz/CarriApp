package com.example.carriapp;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.carriapp.Config.Constantes;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carribar);

        TextView textViewNombreCarribar = (TextView) findViewById(R.id.textViewNombre);

        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, Constantes.BD_NAME)
                .allowMainThreadQueries()
                .build();

//        Carribar carriPrueba = new Carribar("prueba2","AAsaa 7013","20","23",
//                "3434474355", true, true, false, false, false,
//                false, true);
//
//        db.carribarDao().insert(carriPrueba);
//
//        System.out.println(db.carribarDao().count());

        List<Carribar> listaCarribares = db.carribarDao().getAllCarribares();

        Carribar carriPrueba2 = listaCarribares.get(0);

        textViewNombreCarribar.setText(carriPrueba2.getNombre());



    }
}