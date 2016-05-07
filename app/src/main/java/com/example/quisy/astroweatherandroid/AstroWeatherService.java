package com.example.quisy.astroweatherandroid;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;

/**
 * Created by Mariusz on 2016-05-07.
 */
public class AstroWeatherService {

    private static AstroCalculator _astroCalculator;
    private Settings _settings;

    public AstroWeatherService()
    {
        _settings = new Settings();
        _astroCalculator = new AstroCalculator(getCurrentDateTime(),getLocation());
    }


    public AstroCalculator.SunInfo getSunInfo()
    {
        return _astroCalculator.getSunInfo();
    }

    public AstroCalculator.MoonInfo getMoonInfo()
    {
        return  _astroCalculator.getMoonInfo();
    }

    private AstroCalculator.Location getLocation()
    {
        return new AstroCalculator.Location(_settings.Location.Latitude,_settings.Location.Longitude);
    }

    private AstroDateTime getCurrentDateTime()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return new AstroDateTime(year,month,day,hour,minute,second,1,true);
    }

}
