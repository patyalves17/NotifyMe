package com.gmail.reebrando.notifyme;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import java.util.Timer;


/**
 * Created by renanribeirobrando on 18/06/17.
 */

public class NotificationService extends Service{

    private Timer timer;
    private NotifyMe notifyMe;
    private String title;
    private String message;
    private int interval;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        Bundle extras = intent.getExtras();
        if(extras != null){
            this.interval = extras.getInt("interval");
            this.interval = (this.interval * 1000) * 60;
            this.title = extras.getString("title");
            this.message = extras.getString("message");
        }
        timer = new Timer();
        notifyMe = new NotifyMe(getApplicationContext(), this.title, this.message);
        timer.schedule(notifyMe, this.interval, this.interval);
        // stopped, so return sticky.
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        notifyMe.cancel();
        timer.cancel();
        super.onDestroy();
    }
}
