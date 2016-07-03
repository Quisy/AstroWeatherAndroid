package com.example.quisy.astroweatherandroid.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mariusz on 2016-07-03.
 */
public class Item {
    @SerializedName("condition")
    private Condition condition = new Condition();
    @SerializedName("forecast")
    private List<Forecast> forecast = new ArrayList<>();


    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }
}
