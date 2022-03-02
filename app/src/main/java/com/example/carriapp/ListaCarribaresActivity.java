package com.example.carriapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.carriapp.Config.Constantes;
import com.example.carriapp.DataBase.AppDataBase;
import com.example.carriapp.Entidades.CarribarView;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class ListaCarribaresActivity extends AppCompatActivity {

    AppDataBase db;
    RecyclerView listaCarribar;
    List<CarribarView> listaCarribares;
    DrawerLayout drawerLayout;
    TextView textToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        drawerLayout = findViewById(R.id.drawer_layout_ver_carribar);

        textToolBar = findViewById(R.id.textToolBar);
        textToolBar.setText("Carribares abiertos");

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

    public void ClickMenu(View view) {
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void ClickAddCarribar(View view) { MainActivity.redirectActivity(this, AgregarCarribarActivity.class); }

    public void ClickVerCarribar(View view) {
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickSalir(View view) {
        MainActivity.salir(this);
    }

}