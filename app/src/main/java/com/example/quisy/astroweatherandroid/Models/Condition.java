package com.example.quisy.astroweatherandroid.Models;

/**
 * Created by Mariusz on 2016-07-03.
 */
public class Condition {
    private int code;

    private int temp;

    private String text;

    public int getTemp() {
        switch (SharedData.units.getTempUnit())
        {
            case CELSIUS:
                return temp;
            case KELVIN:
                return temp + 273;
            default:
                return temp;
        }
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
