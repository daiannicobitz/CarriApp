package com.example.carriapp;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.carriapp.Config.Constantes;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.Carribar;
import com.example.carriapp.Entidades.CarribarView;

public class DescriptionActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carribar);

        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, Constantes.BD_NAME)
                .allowMainThreadQueries()
                .build();


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

        nombreTextView.setText(item.getNombre());
        direccionTextView.setText(item.getDireccion());
        //distanciaTextView.setText(item.getDistancia);
        //abiertoTextView.setText(item.getNombre());
        //cerradoTextView.setText(item.getNombre());
        horaCierreTextView.setText(item.getHoraCierre());
        horaAperturaTextView.setText(item.getHoraApertura());
        contactoTextView.setText(item.getContacto());
        //hamburguesasTextView.setText(item.getHayHamburguesa());
        //panchosTextView.setText(item.getNombre());
        //choripanesTextView.setText(item.getNombre());
        //milanesaTextView.setText(item.getNombre());
        //pizzaTextView.setText(item.getNombre());
        //bondiolaTextView.setText(item.getNombre());
        //papasFritasTextView.setText(item.getNombre());
    }
}