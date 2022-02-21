package com.example.carriapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

public class CarribaresViewModel extends AndroidViewModel {

        private final RepositorioCarribares mRepository;

        private final List<Carribar> mCarribares;

        public CarribaresViewModel(@NonNull Application application) {
            super(application);
            mRepository = new RepositorioCarribares(application);
            mCarribares = mRepository.getAllCarribares();
        }

        public List<Carribar> getCarribares() {
            return mCarribares;
        }

        public void insert(Carribar shoppingList) {
            mRepository.insert(shoppingList);
        }

    }


