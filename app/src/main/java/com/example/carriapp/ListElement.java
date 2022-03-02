package com.example.carriapp;

public class ListElement {

    //public String color;
    public String nombre;
    public String direccion;
    public String horaCierre;
    public String horaApertura;

    public ListElement(com.example.carriapp.Entidades.CarribarView carribarView) {
        this.nombre = carribarView.getNombre();
        this.direccion = carribarView.getDireccion();
        this.horaCierre = carribarView.getHoraCierre();
        this.horaApertura = carribarView.getHoraApertura();
    }

    /*public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }*/

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

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }
}
