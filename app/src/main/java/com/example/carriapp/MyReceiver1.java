package com.example.carriapp;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class MyReceiver1 extends BroadcastReceiver {

    public static final String EVENTO_01 ="com.example.carriapp.EVENTO_01";

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("Entra Receiver");

        //DECIDO QUE HAGO CON LOS INTENTS
//        StringBuilder sb = new StringBuilder();
//        for(String k : intent.getExtras().keySet()){
//            sb.append(k + intent.getExtras().get(k));
//        }

        Log.d("INTENT_RECIBIDO", intent.getAction()+" : " + intent.getCategories() + " /" + intent.getExtras());

            System.out.println("Toma Intent");

            Intent i1 = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0, i1, 0);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context, MainActivity.CANAL01_MENSAJES_ID)
                            .setSmallIcon (R.drawable.choripan)
                            .setContentTitle("NOTIFICACION 1 CARRIBAR")
                            .setContentText("not 2")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(99, mBuilder.build());


    }
}
