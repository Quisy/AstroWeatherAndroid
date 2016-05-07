package com.example.quisy.astroweatherandroid;

/**
 * Created by Mariusz on 2016-05-07.
 */
public class Settings {

    public Location Location = new Location();
    public Time Time = new Time();

    public static class Location {
        public static double Latitude = 0;
        public static double Longitude = 0;
    }

    public static class Time {
        public static int RefreshTime = 0;
    }

}
