package com.example.quisy.astroweatherandroid.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mariusz on 2016-07-02.
 */
public class Atmosphere {
    @SerializedName("pressure")
    private String pressure;

    @SerializedName("visibility")
    private String visibility;

    @SerializedName("humidity")
    private String humidity;

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
