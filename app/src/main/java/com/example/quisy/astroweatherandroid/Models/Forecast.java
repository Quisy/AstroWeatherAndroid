package com.example.quisy.astroweatherandroid.Models;

import java.util.Date;

/**
 * Created by Mariusz on 2016-07-03.
 */
public class Forecast {
    private String date;

    private String high;

    private String low;

    private String text;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
