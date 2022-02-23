package com.example.carriapp;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button botonAgregar;
    Button botonVer;
    public static final String CANAL01_MENSAJES_ID  = "10001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyReceiver1 rec = new MyReceiver1();
        IntentFilter intFilt = new IntentFilter();
        //REGISTRO LOS INTENT
        intFilt.addAction(MyReceiver1.EVENTO_01);
        this.registerReceiver(rec,intFilt);
        //this.createNotificationChannel();

        botonAgregar = (Button) findViewById(R.id.buttonAgregar);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), AgregarCarribarActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        botonVer = (Button) findViewById(R.id.button2Ver);
        botonVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent (v.getContext(), VerCarribarActivity.class);
//                startActivityForResult(intent, 0);
                Intent i = new Intent();
                i.putExtra (" data1", "A" );
                i.putExtra (" data12", "B" );
                i.setAction (MyReceiver1.EVENTO_01);
               sendBroadcast(i);
            }
        });


    }

    private void createNotificationChannel() {

        System.out.println("Crea Canal");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 =
                    new NotificationChannel(CANAL01_MENSAJES_ID, "CANAL 1", NotificationManager.IMPORTANCE_DEFAULT);
            channel1.setDescription("Descripcion 1");
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);

        }
    }

}