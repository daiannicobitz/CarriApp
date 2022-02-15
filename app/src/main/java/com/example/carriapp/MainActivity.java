package com.example.carriapp;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.carriapp.Config.Constantes;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carribar);


//                Carribar carriPrueba = new Carribar(nombreCarribar ,"AA 7013","20","23",
//                        "3434474355", true, true, false, false, false,
//                        false, true);
//                db.carribarDao().insert(carriPrueba);
//        List<Carribar> listaCarribares = db.carribarDao().getAllCarribares();
//        System.out.println(listaCarribares.get(0).getDireccion());
//        EditText editTextNombreCarri = (EditText) findViewById(R.id.editTextNombre);
//        String textoNombre = editTextNombreCarri.getText().toString();

    }
}

