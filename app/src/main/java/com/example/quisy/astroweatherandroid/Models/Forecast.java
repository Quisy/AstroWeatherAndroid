package com.example.quisy.astroweatherandroid.Models;

import java.util.Date;

/**
 * Created by Mariusz on 2016-07-03.
 */
public class Forecast {
    private String date;

    private int high;

    private int low;

    private String text;

    private int code;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHigh() {
        switch (SharedData.units.getTempUnit())
        {
            case CELSIUS:
                return high;
            case KELVIN:
                return high + 273;
            default:
                return high;
        }
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        switch (SharedData.units.getTempUnit())
        {
            case CELSIUS:
                return low;
            case KELVIN:
                return low + 273;
            default:
                return low;
        }
    }

    public void setLow(int low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
