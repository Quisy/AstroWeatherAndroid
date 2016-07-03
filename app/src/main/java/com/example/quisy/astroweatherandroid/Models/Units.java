package com.example.quisy.astroweatherandroid.Models;

/**
 * Created by Mariusz on 2016-07-03.
 */
public class Units {

    private Temperature tempUnit = Temperature.CELSIUS;
    private Speed speedUnit = Speed.KM_PER_H;


    public Temperature getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(Temperature tempUnit) {
        this.tempUnit = tempUnit;
    }

    public Speed getSpeedUnit() {
        return speedUnit;
    }

    public void setSpeedUnit(Speed speedUnit) {
        this.speedUnit = speedUnit;
    }


    public String getTempUnitShort() {
        switch (tempUnit)
        {
            case CELSIUS:
                return "C";
            case KELVIN:
                return "K";
            default:
                return "C";
        }
    }


    public String getSpeedUnitShort() {
        switch (speedUnit)
        {
            case KM_PER_H:
                return "km/h";
            case METERS_PER_SEC:
                return "m/s";
            default:
                return "km/h";
        }
    }


    public enum Temperature{
        CELSIUS,
        KELVIN
    }

    public enum Speed{
        KM_PER_H,
        METERS_PER_SEC
    }
}


