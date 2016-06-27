package com.example.quisy.astroweatherandroid;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.example.quisy.astroweatherandroid.Models.Moon;
import com.example.quisy.astroweatherandroid.Models.Settings;
import com.example.quisy.astroweatherandroid.Services.LocationService;

public class MoonInfoFragment extends Fragment {


    TextView lblMoonrise, lblMoonset, lblNextFullMoon, lblNextNewMoon, lblMoonAge, lblMoonIllumination;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_moon_info, container, false);


        lblMoonrise = (TextView) rootView.findViewById(R.id.lblMoonrise);
        lblMoonset = (TextView) rootView.findViewById(R.id.lblMoonset);
        lblNextFullMoon = (TextView) rootView.findViewById(R.id.lblNextFullMoon);
        lblNextNewMoon = (TextView) rootView.findViewById(R.id.lblNextNewMoon);
        lblMoonAge = (TextView) rootView.findViewById(R.id.lblMoonAge);
        lblMoonIllumination = (TextView) rootView.findViewById(R.id.lblMoonIllumination);



        lblMoonrise.setText("Moonrise: " + Moon.Moonrise);
        lblMoonset.setText("Sunset: " + Moon.MoonSet);
        lblNextFullMoon.setText("Next full moon: " + Moon.NextFullMoon);
        lblNextNewMoon.setText("Next new moon: " + Moon.NextNewMoon);
        lblMoonAge.setText("Age: " + Moon.Age);
        lblMoonIllumination.setText("Illumination: " + Moon.Illumination);


        final Handler hMoon = new Handler();
        final int delay = Settings.Time.RefreshTime * 60000;

        if (delay > 0) {

            hMoon.postDelayed(new Runnable() {
                public void run() {

                    lblMoonrise.setText("Moonrise: " + Moon.Moonrise);
                    lblMoonset.setText("Sunset: " + Moon.MoonSet);
                    lblNextFullMoon.setText("Next full moon: " + Moon.NextFullMoon);
                    lblNextNewMoon.setText("Next new moon: " + Moon.NextNewMoon);
                    lblMoonAge.setText("Age: " + Moon.Age);
                    lblMoonIllumination.setText("Illumination: " + Moon.Illumination);

                    hMoon.postDelayed(this, delay);
                }
            }, delay);

        }




        return rootView;
    }
}
