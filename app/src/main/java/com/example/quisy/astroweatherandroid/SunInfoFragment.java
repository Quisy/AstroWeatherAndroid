package com.example.quisy.astroweatherandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;


/**
 * Created by Mariusz on 2016-05-07.
 */
public class SunInfoFragment extends Fragment {


    private AstroWeatherService _astroWeatherService;
    TextView lblSunrise, lblSunriseAzimuth, lblSunset, lblSunsetAzimuth, lblTwilightMorning, lblTwilightEvening;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_sun_info, container, false);


        _astroWeatherService = new AstroWeatherService();
        lblSunrise = (TextView) rootView.findViewById(R.id.lblSunrise);
        lblSunriseAzimuth = (TextView) rootView.findViewById(R.id.lblSunriseAzimuth);
        lblSunset = (TextView) rootView.findViewById(R.id.lblSunset);
        lblSunsetAzimuth = (TextView) rootView.findViewById(R.id.lblSunsetAzimuth);
        lblTwilightMorning = (TextView) rootView.findViewById(R.id.lblTwilightMorning);
        lblTwilightEvening = (TextView) rootView.findViewById(R.id.lblTwilightEvening);


        AstroCalculator.SunInfo sunInfo = _astroWeatherService.getSunInfo();



        lblSunrise.setText("Sunrise: " + sunInfo.getSunrise().toString());
        lblSunriseAzimuth.setText("Sunrise azimuth: " + String.valueOf(sunInfo.getAzimuthRise()));
        lblSunset.setText("Sunset: " + sunInfo.getSunset().toString());
        lblSunsetAzimuth.setText("Sunrise azimuth: " + String.valueOf(sunInfo.getAzimuthRise()));
        lblTwilightMorning.setText("Twilight morning: " + sunInfo.getTwilightMorning().toString());
        lblTwilightEvening.setText("Twilight evening: " + sunInfo.getTwilightEvening().toString());




        return rootView;
    }
}
