package com.example.quisy.astroweatherandroid;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;

public class MoonInfoFragment extends Fragment {

    private AstroWeatherService _astroWeatherService;
    TextView lblMoonrise, lblMoonset, lblNextFullMoon, lblNextNewMoon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_moon_info, container, false);


        _astroWeatherService = new AstroWeatherService();
        lblMoonrise = (TextView) rootView.findViewById(R.id.lblMoonrise);
        lblMoonset = (TextView) rootView.findViewById(R.id.lblMoonset);
        lblNextFullMoon = (TextView) rootView.findViewById(R.id.lblNextFullMoon);
        lblNextNewMoon = (TextView) rootView.findViewById(R.id.lblNextNewMoon);


        AstroCalculator.MoonInfo moonInfo = _astroWeatherService.getMoonInfo();


        lblMoonrise.setText("Moonrise: " + moonInfo.getMoonrise().toString());
        lblMoonset.setText("Sunset: " + moonInfo.getMoonset().toString());
        lblNextFullMoon.setText("Next full moon: " + moonInfo.getNextFullMoon().toString());
        lblNextNewMoon.setText("Next new moon: " + moonInfo.getNextNewMoon().toString());


        final Handler hMoon = new Handler();
        final int delay = Settings.Time.RefreshTime * 60000;

        if (delay > 0) {

            hMoon.postDelayed(new Runnable() {
                public void run() {
                    AstroCalculator.MoonInfo moonInfo = _astroWeatherService.getMoonInfo();

                    lblMoonrise.setText("Moonrise: " + moonInfo.getMoonrise().toString());
                    lblMoonset.setText("Sunset: " + moonInfo.getMoonset().toString());
                    lblNextFullMoon.setText("Next full moon: " + moonInfo.getNextFullMoon().toString());
                    lblNextNewMoon.setText("Next new moon: " + moonInfo.getNextNewMoon().toString());
                    System.out.println("test2");
                    hMoon.postDelayed(this, delay);
                }
            }, delay);

        }


        return rootView;
    }
}
