package com.example.quisy.astroweatherandroid.Models;

/**
 * Created by Mariusz on 2016-06-27.
 */
public class Location {
    private String name;
    private String woeid;
    private String latitude = "0";
    private String longitude= "0";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getWoeid() {
        return woeid;
    }

    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean isEqual= false;

        if (object != null && object instanceof Location)
        {
            isEqual = (Integer.parseInt(this.woeid) == Integer.parseInt(((Location) object).woeid));
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.woeid);
    }
}
