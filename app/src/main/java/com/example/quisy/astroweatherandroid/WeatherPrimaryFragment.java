package com.example.quisy.astroweatherandroid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quisy.astroweatherandroid.Models.SharedData;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class WeatherPrimaryFragment extends Fragment{

    private TextView lblLocationName, lblCoordinates, lblTemperature, lblPressure, lblCondition;
    private ImageView conditionImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_weather_primary, container, false);


        initializeControls(rootView);
        try
        {
            setText();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }




        return rootView;

    }

    private void setText() throws IOException {
        lblLocationName.setText(SharedData.currentLocation.getName());
        lblCoordinates.setText(SharedData.currentLocation.getLatitude() + ", " + SharedData.currentLocation.getLongitude());
        lblTemperature.setText("Temperature: " + String.valueOf(SharedData.weatherInfo.item.getCondition().getTemp()) + " " + SharedData.units.getTempUnitShort());
        lblPressure.setText("Pressure: " + SharedData.weatherInfo.atmosphere.getPressure() + " hPa");
        lblCondition.setText(SharedData.weatherInfo.item.getCondition().getText());


        InputStream is = (InputStream) new URL("http://l.yimg.com/a/i/us/we/52/" + String.valueOf(SharedData.weatherInfo.item.getCondition().getCode()) + ".gif").getContent();
        Drawable d = Drawable.createFromStream(is, "src name");

        conditionImage.setImageDrawable(d);
    }


    private void initializeControls(ViewGroup rootView)
    {
        lblLocationName = (TextView) rootView.findViewById(R.id.lblLocationName);
        lblCoordinates = (TextView) rootView.findViewById(R.id.lblCoordinates);
        lblTemperature = (TextView) rootView.findViewById(R.id.lblTemperature);
        lblPressure = (TextView) rootView.findViewById(R.id.lblPressure);
        lblCondition = (TextView) rootView.findViewById(R.id.lblCondition);

        conditionImage = (ImageView) rootView.findViewById(R.id.conditionImage);
    }



}
