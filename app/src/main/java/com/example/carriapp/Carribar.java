package com.example.carriapp;

import android.support.annotation.NonNull;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lista_carribares")
public class Carribar {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer idCarribar;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "direccion")
    private String direccion;

    @NonNull
    @ColumnInfo(name = "hora_apertura")
    private String horaApertura;

    @NonNull
    @ColumnInfo(name = "hora_cierre")
    private String horaCierre;

    @NonNull
    @ColumnInfo(name = "contacto")
    private String contacto;

    @NonNull
    @ColumnInfo(name = "hamburguesa")
    private Boolean hayHamburguesa;

    @NonNull
    @ColumnInfo(name = "choripan")
    private Boolean hayChoripan;

    @NonNull
    @ColumnInfo(name = "pizza")
    private Boolean hayPizza;

    @NonNull
    @ColumnInfo(name = "papas_fritas")
    private Boolean hayPapasFritas;
    @NonNull
    @ColumnInfo(name = "pancho")
    private Boolean hayPancho;

    @NonNull
    @ColumnInfo(name = "milanesa")
    private Boolean hayMilanesa;

    @NonNull
    @ColumnInfo(name = "bondiola")
    private Boolean hayBondiola;

    public Carribar(@NonNull Integer idCarribar, @NonNull String nombre,
                    @NonNull String direccion, @NonNull String horaApertura,
                    @NonNull String horaCierre, @NonNull String contacto,
                    @NonNull Boolean hayHamburguesa, @NonNull Boolean hayChoripan,
                    @NonNull Boolean hayPizza, @NonNull Boolean hayPapasFritas,
                    @NonNull Boolean hayPancho, @NonNull Boolean hayMilanesa,
                    @NonNull Boolean hayBondiola) {
        this.idCarribar = idCarribar;
        this.nombre = nombre;
        this.direccion = direccion;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.contacto = contacto;
        this.hayHamburguesa = hayHamburguesa;
        this.hayChoripan = hayChoripan;
        this.hayPizza = hayPizza;
        this.hayPapasFritas = hayPapasFritas;
        this.hayPancho = hayPancho;
        this.hayMilanesa = hayMilanesa;
        this.hayBondiola = hayBondiola;
    }

    public Integer getIdCarribar() {
        return idCarribar;
    }

    public void setIdCarribar(Integer idCarribar) {
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

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Boolean getHayHamburguesa() {
        return hayHamburguesa;
    }

    public void setHayHamburguesa(Boolean hayHamburguesa) {
        this.hayHamburguesa = hayHamburguesa;
    }

    public Boolean getHayChoripan() {
        return hayChoripan;
    }

    public void setHayChoripan(Boolean hayChoripan) {
        this.hayChoripan = hayChoripan;
    }

    public Boolean getHayPizza() {
        return hayPizza;
    }

    public void setHayPizza(Boolean hayPizza) {
        this.hayPizza = hayPizza;
    }

    public Boolean getHayPapasFritas() {
        return hayPapasFritas;
    }

    public void setHayPapasFritas(Boolean hayPapasFritas) {
        this.hayPapasFritas = hayPapasFritas;
    }

    public Boolean getHayPancho() {
        return hayPancho;
    }

    public void setHayPancho(Boolean hayPancho) {
        this.hayPancho = hayPancho;
    }

    public Boolean getHayMilanesa() {
        return hayMilanesa;
    }

    public void setHayMilanesa(Boolean hayMilanesa) {
        this.hayMilanesa = hayMilanesa;
    }

    public Boolean getHayBondiola() {
        return hayBondiola;
    }

    public void setHayBondiola(Boolean hayBondiola) {
        this.hayBondiola = hayBondiola;
    }
}
