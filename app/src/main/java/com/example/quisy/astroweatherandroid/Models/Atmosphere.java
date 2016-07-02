package com.example.quisy.astroweatherandroid.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mariusz on 2016-07-02.
 */
public class Atmosphere {
    @SerializedName("pressure")
    private String pressure;

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
