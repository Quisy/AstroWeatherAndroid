package com.example.quisy.astroweatherandroid;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quisy.astroweatherandroid.Models.Settings;
import com.example.quisy.astroweatherandroid.Models.SharedData;
import com.example.quisy.astroweatherandroid.Services.WeatherService;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SettingsInfoFragment extends Fragment {

    TextView lblTime, lblLocation;
    Button btnRefreshData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_settings_info, container, false);

        btnRefreshData = (Button) rootView.findViewById(R.id.btnRefreshData);

        lblTime = (TextView) rootView.findViewById(R.id.lblTime);
        lblLocation = (TextView) rootView.findViewById(R.id.lblLocation);

        //lblLocation.setText("Location: " + Double.toString(Settings.Location.Longitude) + ", " + Double.toString(Settings.Location.Latitude));
        lblLocation.setText("Location: " +  SharedData.currentLocation.getName() + "   " + SharedData.currentLocation.getLongitude() + ", " + SharedData.currentLocation.getLatitude());

        final Handler hTime = new Handler();

        hTime.postDelayed(new Runnable() {
            public void run() {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();

                lblTime.setText("Time: " + dateFormat.format(cal.getTime()));

                hTime.postDelayed(this, 1000);
            }
        }, 1000);



        btnRefreshData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeatherService ws = new WeatherService(getActivity().getApplicationContext());
                ws.downloadWeatherInfo(SharedData.currentLocation.getWoeid());
                Toast.makeText(getActivity().getApplicationContext(), "Data refreshed", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }


}
