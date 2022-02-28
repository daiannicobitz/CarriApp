package com.example.carriapp.Entidades;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(tableName = CarribarView.TABLE_NAME)
public class CarribarView {

    public static final String TABLE_NAME = "lista_carribares";
    public static final String COLUMN_ID = "id";

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "direccion")
    private String direccion;

    @NonNull
    @ColumnInfo(name = "hora_cierre")
    private String horaCierre;

    public CarribarView( @NonNull String nombre, @NonNull String direccion, @NonNull String horaCierre) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.horaCierre = horaCierre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }


}
