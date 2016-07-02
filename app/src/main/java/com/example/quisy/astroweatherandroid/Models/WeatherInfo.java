package com.example.quisy.astroweatherandroid.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mariusz on 2016-07-02.
 */
public class WeatherInfo {
    @SerializedName("atmosphere")
    public Atmosphere atmosphere;
    @SerializedName("wind")
    public Wind wind;
}
