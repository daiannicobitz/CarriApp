package com.example.carriapp;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CarribaresViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView dbText = findViewById(R.id.db_text);

        Carribar prueba = new Carribar(1);


        ViewModelProvider.AndroidViewModelFactory factory =
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

        mViewModel = new ViewModelProvider(this, factory)
                .get(CarribaresViewModel.class);

        dbText.setText(mViewModel.getCarribares().get(0).getNombre());

//        mViewModel.getCarribares().observe(this, carribares -> {
//                    StringBuilder sb = new StringBuilder();
//                    for (Carribar carri : carribares) {
//                        sb.append(carri.getNombre()).append("\n");
//                    }
//                    dbText.setText(sb.toString());
//                }
//        );

    }
}