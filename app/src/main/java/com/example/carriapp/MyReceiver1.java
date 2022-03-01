package com.example.carriapp;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class MyReceiver1 extends BroadcastReceiver {

    public static final String EVENTO_01 ="com.example.carriapp.EVENTO_01";

    @Override
    public void onReceive(Context context, Intent intent) {

        // Aca podriamos decidir que hacer cuando se clickee la notificacion
            Intent i1 = new Intent(context, ListaCarribaresActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0, i1, 0);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context, AgregarCarribarActivity.CANAL01_MENSAJES_ID)
                            .setSmallIcon (R.drawable.choripan)
                            .setContentTitle("Carribar creado correctamente")
                            .setContentText("Felicitaciones, usted a creado el carribar y ya esta disponible para los demas usuarios")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(99, mBuilder.build());


    }
}
