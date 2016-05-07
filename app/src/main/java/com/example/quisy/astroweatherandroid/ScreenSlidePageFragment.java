package com.example.quisy.astroweatherandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;


/**
 * Created by Mariusz on 2016-05-07.
 */
public class ScreenSlidePageFragment extends Fragment {


    private AstroWeatherService _astroWeatherService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);


        _astroWeatherService = new AstroWeatherService();

        TextView title = (TextView) rootView.findViewById(R.id.textTitle);
        TextView content = (TextView) rootView.findViewById(R.id.textInfo);




        return rootView;
    }
}
