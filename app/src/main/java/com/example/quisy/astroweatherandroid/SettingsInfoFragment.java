package com.example.quisy.astroweatherandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quisy.astroweatherandroid.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SettingsInfoFragment extends Fragment {

    TextView lblTime, lblLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_settings_info, container, false);

        lblTime = (TextView) rootView.findViewById(R.id.lblTime);
        lblLocation = (TextView) rootView.findViewById(R.id.lblLocation);

        lblLocation.setText("Location: " + Double.toString(Settings.Location.Longitude) + ", " + Double.toString(Settings.Location.Latitude));

        final Handler hTime = new Handler();

        hTime.postDelayed(new Runnable() {
            public void run() {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();

                lblTime.setText("Time: " + dateFormat.format(cal.getTime()));

                hTime.postDelayed(this, 1000);
            }
        }, 1000);


        return rootView;
    }


}
