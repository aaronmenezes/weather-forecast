package com.kyser.weatherforecast.model;

import java.io.Serializable;

public class FeelsLike implements Serializable {

    private String day;
    private String night;
    private String eve;
    private String morn;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getEve() {
        return eve;
    }

    public void setEve(String eve) {
        this.eve = eve;
    }

    public String getMorn() {
        return morn;
    }

    public void setMorn(String morn) {
        this.morn = morn;
    }
}
