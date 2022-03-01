package com.example.carriapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.carriapp.Config.Constantes;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.CarribarView;

import java.util.ArrayList;
import java.util.List;

public class ListaCarribaresActivity extends AppCompatActivity {

    AppDataBase db;
    RecyclerView listaCarribar;
    List<CarribarView> listaCarribares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        db = Room.databaseBuilder(getApplicationContext(),AppDataBase.class, Constantes.BD_NAME)
                .allowMainThreadQueries()
                .build();

        listaCarribar = findViewById(R.id.listRecyclerView);
        listaCarribar.setLayoutManager(new LinearLayoutManager(this));

        listaCarribares = new ArrayList<>();
        listaCarribares = db.carribarDao().getAllCarribaresView();


        ListAdapter listaCarribaresAdapter = new ListAdapter(listaCarribares, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarribarView item) {
                moveToDescription(item);
            }
        });
        listaCarribar.setAdapter(listaCarribaresAdapter);

        db.close();
    }

    public void moveToDescription(CarribarView item){
        Intent intent = new Intent(this, VerCarribarActivity.class);
        intent.putExtra("CarribarView", item);
        startActivity(intent);
    }

}