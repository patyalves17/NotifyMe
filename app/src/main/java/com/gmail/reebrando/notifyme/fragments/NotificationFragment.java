package com.gmail.reebrando.notifyme.fragments;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompatExtras;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.gmail.reebrando.notifyme.MainActivity;
import com.gmail.reebrando.notifyme.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by renanribeirobrando on 15/06/17.
 */

public class NotificationFragment extends Fragment {

    @BindView(R.id.etInterval)
    TextInputLayout etInterval;

    public NotificationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.notification_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @OnClick(R.id.btnGenerate)
    public void generateTimetable(View view){
        if (etInterval.getEditText().getText().toString().isEmpty()) {
            Snackbar.make(view, R.string.empty_input, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else{
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(view.getContext())
                            .setSmallIcon(R.drawable.ic_clock)
                            .setTicker("New alert")
                            .setSound(Uri.parse("android.resource://"+getActivity().getPackageName()+"/raw/toasty"))
                            .setContentTitle("Notifications Example")
                            .setContentText("This is a test notification");

            Intent notificationIntent = new Intent(view.getContext(), MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(view.getContext(), 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);

            // Add as notification
            NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }
    }


}
