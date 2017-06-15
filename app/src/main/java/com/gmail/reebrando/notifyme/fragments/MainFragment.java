package com.gmail.reebrando.notifyme.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gmail.reebrando.notifyme.AlarmReceiver;
import com.gmail.reebrando.notifyme.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by renanribeirobrando on 15/06/17.
 */

public class MainFragment extends Fragment {

    @BindView(R.id.edTime)
    EditText edTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @OnClick(R.id.btnAlarm)
    public void setAlarm(View view){
        int i = Integer.parseInt(edTime.getText().toString());

        Intent intent = new Intent(view.getContext(), AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(view.getContext(), 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i + 1000), pendingIntent);

        Snackbar.make(view, getString(R.string.alarm_msg_one) + i + getString(R.string.alarm_msg_two), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
