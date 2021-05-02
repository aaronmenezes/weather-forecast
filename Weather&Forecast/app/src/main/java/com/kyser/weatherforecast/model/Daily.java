package com.kyser.weatherforecast.model;

import java.io.Serializable;
import java.util.List;

public class Daily implements Serializable {

    private int dt;
    int sunrise;
    int sunset;
    private Temp temp;
    private FeelsLike feelsLike;
    private int pressure;
    private int humidity;
    private Float dewPoint;
    private String wind_speed;
    private int windDeg;
    private List<Weather> weather;
    private int clouds;
    private Float uvi;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public FeelsLike getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(FeelsLike feelsLike) {
        this.feelsLike = feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Float getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Float dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getWindSpeed() {
        return wind_speed;
    }

    public void setWindSpeed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public Float getUvi() {
        return uvi;
    }

    public void setUvi(Float uvi) {
        this.uvi = uvi;
    }
}
