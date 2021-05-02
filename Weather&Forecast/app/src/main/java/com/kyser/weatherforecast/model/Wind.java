package com.kyser.weatherforecast.model;

public class Wind {
    private String deg;
    private String speed;
    private String wind_gust;

    public String getWind_gust() {
        return wind_gust;
    }

    public void setWind_gust(String wind_gust) {
        this.wind_gust = wind_gust;
    }

    public String getDeg ()  {
        return deg;
    }

    public void setDeg (String deg) {
        this.deg = deg;
    }

    public String getSpeed () {
        return speed;
    }

    public void setSpeed (String speed)  {
        this.speed = speed;
    }
}
