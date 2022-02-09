package com.example.carriapp;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        setContentView(R.layout.activity_agregar_carribar);

        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, Constantes.BD_NAME)
                .allowMainThreadQueries()
                .build();

        Button botonAgregar = (Button) findViewById(R.id.button);

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editTextNombreCarri = (EditText) findViewById(R.id.editTextNombre);
                String nombreCarribar = editTextNombreCarri.getText().toString();

                Carribar carriPrueba = new Carribar(nombreCarribar ,"AA 7013","20","23",
                        "3434474355", true, true, false, false, false,
                        false, true);

                db.carribarDao().insert(carriPrueba);

                System.out.println("ACA LLEGÓ");
            }
        });



        System.out.println(db.carribarDao().count());

        List<Carribar> listaCarribares = db.carribarDao().getAllCarribares();

        System.out.println(listaCarribares.get(0).getDireccion());

    }
}