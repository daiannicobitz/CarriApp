package com.example.carriapp;

import android.arch.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarribaresDAO {

    @Query("SELECT * FROM lista_carribares")
    List<Carribar> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Carribar carribar);


}
