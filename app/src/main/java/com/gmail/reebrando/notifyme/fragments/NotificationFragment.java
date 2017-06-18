package com.gmail.reebrando.notifyme.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gmail.reebrando.notifyme.NotificationService;
import com.gmail.reebrando.notifyme.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by renanribeirobrando on 15/06/17.
 */

public class NotificationFragment extends Fragment {


    @BindView(R.id.etTitle) TextInputLayout etTitle;
    @BindView(R.id.etMessage) TextInputLayout etMessage;
    @BindView(R.id.etInterval) TextInputLayout etInterval;

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
        if (etTitle.getEditText().getText().toString().isEmpty() || etMessage.getEditText().getText().toString().isEmpty()|| etInterval.getEditText().getText().toString().isEmpty() ) {
            Snackbar.make(view, R.string.empty_input, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else{
            Intent intent = new Intent(view.getContext(), NotificationService.class);
            intent.putExtra("interval", Integer.parseInt(etInterval.getEditText().getText().toString()));
            intent.putExtra("title", etTitle.getEditText().getText().toString());
            intent.putExtra("message", etMessage.getEditText().getText().toString());
            view.getContext().startService(intent);

            Snackbar.make(view, R.string.reminder, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            MainFragment mainFragment = new MainFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_main, mainFragment);
            transaction.disallowAddToBackStack();
            transaction.commit();
        }
    }


}
