package com.example.carriapp.Entidades;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = CarribarView.TABLE_NAME)
public class CarribarView implements Serializable {

    public static final String TABLE_NAME = "lista_carribares";
    public static final String COLUMN_ID = "id";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(index = true, name = Carribar.COLUMN_ID)
    private long idCarribar;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "direccion")
    private String direccion;

    @NonNull
    @ColumnInfo(name = "hora_cierre")
    private String horaCierre;

    @NonNull
    @ColumnInfo(name = "hora_apertura")
    private String horaApertura;

    public CarribarView( @NonNull String nombre, @NonNull String direccion, @NonNull String horaCierre , @NonNull String horaApertura) {
        //this.idCarribar = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.horaCierre = horaCierre;
        this.horaApertura = horaApertura;
    }
    public long getIdCarribar() {
        return idCarribar;
    }

    public void setIdCarribar(long idCarribar) {
        this.idCarribar = idCarribar;
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


    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura( String horaApertura) {
        this.horaApertura = horaApertura;
    }
}
