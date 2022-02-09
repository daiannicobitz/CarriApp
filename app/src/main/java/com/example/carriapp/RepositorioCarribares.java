package com.example.carriapp;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

public class RepositorioCarribares {

    private final List<Carribar> mCarribares;
    private final CarribaresDAO mCarribaresDao;

    public RepositorioCarribares(Context context) {
        CarribaresDatabase db = CarribaresDatabase.getInstance(context);
        mCarribaresDao = db.carribaresDAO();
        mCarribares = mCarribaresDao.getAll();
    }

    public List<Carribar> getAllCarribares() {
        return mCarribares;
    }

    public void insert(Carribar shoppingList) {
        CarribaresDatabase.dbExecutor.execute(
                () -> mCarribaresDao.insert(shoppingList)
        );
    }

}
