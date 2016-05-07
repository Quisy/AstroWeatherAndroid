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

    private Settings.Location locationSettings;
    private Settings.Time timeSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);


        timeSettings = new Settings.Time();
        locationSettings = new Settings.Location();

        TextView title = (TextView) rootView.findViewById(R.id.textTitle);
        title.setText(String.valueOf(timeSettings.RefrehTime));

        TextView content = (TextView) rootView.findViewById(R.id.textInfo);
        content.setText(String.valueOf(locationSettings.Latitude) + ", " + String.valueOf(locationSettings.Longitude));

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        AstroDateTime dateTime = new AstroDateTime(year,month,day,hour,minute,second,1,true);

        AstroCalculator.Location location = new AstroCalculator.Location(10,10);

        AstroCalculator astroCalculator = new AstroCalculator(dateTime,location);

        AstroCalculator.MoonInfo moonInfo = astroCalculator.getMoonInfo();



        return rootView;
    }
}
