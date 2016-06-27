package com.example.quisy.astroweatherandroid;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.example.quisy.astroweatherandroid.Models.Settings;
import com.example.quisy.astroweatherandroid.Models.Sun;


/**
 * Created by Mariusz on 2016-05-07.
 */
public class SunInfoFragment extends Fragment {


    TextView lblSunrise, lblSunriseAzimuth, lblSunset, lblSunsetAzimuth, lblTwilightMorning, lblTwilightEvening, lblTime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_sun_info, container, false);


        lblSunrise = (TextView) rootView.findViewById(R.id.lblSunrise);
        lblSunriseAzimuth = (TextView) rootView.findViewById(R.id.lblSunriseAzimuth);
        lblSunset = (TextView) rootView.findViewById(R.id.lblSunset);
        lblSunsetAzimuth = (TextView) rootView.findViewById(R.id.lblSunsetAzimuth);
        lblTwilightMorning = (TextView) rootView.findViewById(R.id.lblTwilightMorning);
        lblTwilightEvening = (TextView) rootView.findViewById(R.id.lblTwilightEvening);


        lblSunrise.setText("Sunrise: " + Sun.Sunrise);
        lblSunriseAzimuth.setText("Sunrise azimuth: " + Sun.SunriseAzimuth);
        lblSunset.setText("Sunset: " + Sun.Sunset);
        lblSunsetAzimuth.setText("Sunrise azimuth: " + Sun.SunsetAzimuth);
        lblTwilightMorning.setText("Twilight morning: " + Sun.TwilightMorning);
        lblTwilightEvening.setText("Twilight evening: " + Sun.TwilightMorning);


        final Handler hSun = new Handler();
        final int delay = Settings.Time.RefreshTime * 60000;

        if (delay > 0) {

            hSun.postDelayed(new Runnable() {
                public void run() {

                    lblSunrise.setText("Sunrise: " + Sun.Sunrise);
                    lblSunriseAzimuth.setText("Sunrise azimuth: " + Sun.SunriseAzimuth);
                    lblSunset.setText("Sunset: " + Sun.Sunset);
                    lblSunsetAzimuth.setText("Sunrise azimuth: " + Sun.SunsetAzimuth);
                    lblTwilightMorning.setText("Twilight morning: " + Sun.TwilightMorning);
                    lblTwilightEvening.setText("Twilight evening: " + Sun.TwilightMorning);
                    hSun.postDelayed(this, delay);
                }
            }, delay);

        }





        return rootView;
    }
}
