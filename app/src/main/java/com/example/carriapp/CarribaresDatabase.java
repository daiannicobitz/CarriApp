package com.example.carriapp;


import android.content.Context;
import android.support.annotation.NonNull;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Carribar.class}, version = 1, exportSchema = false)
public abstract class CarribaresDatabase extends RoomDatabase {

    public abstract CarribaresDAO carribaresDAO();

    private static final String DATABASE_NAME = "carribares-db";

    private static CarribaresDatabase INSTANCE;

    private static final int THREADS = 4;

    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREADS);

    public static CarribaresDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (CarribaresDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(), CarribaresDatabase.class,
                            DATABASE_NAME)
                            .addCallback(mRoomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback mRoomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            dbExecutor.execute(() -> {
                CarribaresDAO dao = INSTANCE.carribaresDAO();

                Carribar carribar1 = new Carribar(1,"AAAA", "BBBB","CCCCC","DDDDD","EEEEE",true,true,true,true,true,true,true);

                dao.insert(carribar1);
                System.out.println("LLego");
            });
        }
    };



}
