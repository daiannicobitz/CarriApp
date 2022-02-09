package com.example.carriapp.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.carriapp.Entidades.Carribar;
import com.example.carriapp.Interface.CarribarDAO;

@Database(entities = {Carribar.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    @SuppressWarnings("WeakerAccess")
    public abstract CarribarDAO carribarDao();
    /**
     * The only instance
     */
    private static AppDataBase sInstance; //variable.
}
