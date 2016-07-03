package com.example.quisy.astroweatherandroid.Models;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

/**
 * Created by Mariusz on 2016-07-02.
 */
public class WeatherInfo {
    @SerializedName("atmosphere")
    public Atmosphere atmosphere = new Atmosphere();
    @SerializedName("wind")
    public Wind wind = new Wind();
    @SerializedName("item")
    public Item item = new Item();
    @SerializedName("gettingDate")
    public DateTime gettingDate = new DateTime();

}
