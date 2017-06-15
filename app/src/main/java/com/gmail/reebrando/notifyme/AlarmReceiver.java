package com.gmail.reebrando.notifyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

/**
 * Created by renanribeirobrando on 15/06/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver() {

    }

    private MediaPlayer mediaPlayer = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        mediaPlayer = MediaPlayer.create(context, R.raw.toasty);
        mediaPlayer.start();
        Toast.makeText(context, R.string.alarm, Toast.LENGTH_SHORT).show();
    }
}
