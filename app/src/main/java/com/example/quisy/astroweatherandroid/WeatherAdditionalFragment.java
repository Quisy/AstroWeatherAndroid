package com.example.quisy.astroweatherandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quisy.astroweatherandroid.Models.SharedData;


public class WeatherAdditionalFragment extends Fragment {

    private TextView lblWindSpeed, lblHumidity, lblVisibility;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_weather_additional, container, false);


        initializeControls(rootView);
        setText();




        return rootView;

    }

    private void setText() {
        lblWindSpeed.setText("Wind speed: " + String.valueOf(SharedData.weatherInfo.wind.getSpeed()) + " " + SharedData.units.getSpeedUnitShort());
        lblHumidity.setText("Humidity: " + SharedData.weatherInfo.atmosphere.getHumidity() + " %");
        lblVisibility.setText("Visibility: " + SharedData.weatherInfo.atmosphere.getVisibility() + " %");

    }


    private void initializeControls(ViewGroup rootView)
    {
        lblWindSpeed = (TextView) rootView.findViewById(R.id.lblWindSpeed);
        lblHumidity = (TextView) rootView.findViewById(R.id.lblHumidity);
        lblVisibility = (TextView) rootView.findViewById(R.id.lblVisibility);

    }


}
