package com.example.quisy.astroweatherandroid.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mariusz on 2016-07-02.
 */
public class Wind {
    @SerializedName("direction")
    private String direction;
    @SerializedName("speed")
    private String speed;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
