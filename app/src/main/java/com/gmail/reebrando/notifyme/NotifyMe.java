package com.gmail.reebrando.notifyme;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.util.TimerTask;

/**
 * Created by renanribeirobrando on 18/06/17.
 */

public class NotifyMe extends TimerTask {

    private Context context;
    private String title;
    private String message;

    public NotifyMe(Context context, String title, String message ){
        this.context = context;
        this.title = title;
        this.message = message;
    }

    @Override
    public void run() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_clock)
                        .setTicker("New alert")
                        .setSound(Uri.parse("android.resource://"+ context.getPackageName() +"/raw/toasty"))
                        .setContentTitle(this.title)
                        .setContentText(this.message);

        Intent notificationIntent = new Intent( context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
