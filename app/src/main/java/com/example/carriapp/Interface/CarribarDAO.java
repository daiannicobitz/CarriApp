package com.example.carriapp.Interface;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.carriapp.Entidades.Carribar;
import com.example.carriapp.Entidades.CarribarView;

import java.util.List;

@Dao
public interface CarribarDAO {

    //aqui declaramos los metodos o las acciones para la bd
    //seleccionar cantidades
    @Query("SELECT COUNT(*) FROM " + Carribar.TABLE_NAME)
    int count(); //metodo

    //seleccionar all
    @Query("SELECT * FROM "+Carribar.TABLE_NAME)
    List<Carribar> getAllCarribares();

    //seleccionar all
    @Query("SELECT * FROM "+ CarribarView.TABLE_NAME)
    List<CarribarView> getAllCarribaresView();


    //insertar
    @Insert
    void instarAll(Carribar ... carribares);

    //eliminar
    @Query("DELETE FROM " + Carribar.TABLE_NAME + " WHERE " + Carribar.COLUMN_ID + " = :id")
    int deleteById(long id);

    //actualizar
    @Update
    int updateEntidad(Carribar obj);


    //insertar 2
    @Insert
    long insert(Carribar carribares);
}
