package com.example.quisy.astroweatherandroid.Models;

import com.google.gson.annotations.SerializedName;

import java.math.RoundingMode;
import java.text.DecimalFormat;


/**
 * Created by Mariusz on 2016-07-02.
 */
public class Wind {
    @SerializedName("direction")
    private String direction;
    @SerializedName("speed")
    private double speed;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        switch (SharedData.units.getSpeedUnit()) {
            case KM_PER_H:
                return speed;
            case METERS_PER_SEC:
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);
                double val = (speed) * (10.00/36.00);
                String valS = df.format(val);
                return Double.parseDouble(valS);
            default:
                return speed;
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
