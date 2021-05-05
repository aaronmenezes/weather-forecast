package com.kyser.weatherforecast.model;

import java.util.List;

public class CurrentModel {

    private String visibility;
    private String timezone;
    private MainWeather main;
    private Clouds clouds;
    private Sys sys;
    private String dt;
    private Coordinates coord;
    private List<Weather> weather;
    private String name;
    private String cod;
    private String id;
    private String base;
    private Wind wind;

    public String getVisibility (){
        return visibility;
    }

    public void setVisibility (String visibility){
        this.visibility = visibility;
    }

    public String getTimezone (){
        return timezone;
    }

    public void setTimezone (String timezone){
        this.timezone = timezone;
    }

    public MainWeather getMain (){
        return main;
    }

    public void setMain (MainWeather main) {
        this.main = main;
    }

    public Clouds getClouds () {
        return clouds;
    }

    public void setClouds (Clouds clouds) {
        this.clouds = clouds;
    }

    public Sys getSys () {
        return sys;
    }

    public void setSys (Sys sys) {
        this.sys = sys;
    }

    public String getDt () {
        return dt;
    }

    public void setDt (String dt) {
        this.dt = dt;
    }

    public Coordinates getCoord () {
        return coord;
    }

    public void setCoord (Coordinates coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather () {
        return weather;
    }

    public void setWeather (List<Weather> weather) {
        this.weather = weather;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getCod () {
        return cod;
    }

    public void setCod (String cod) {
        this.cod = cod;
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getBase () {
        return base;
    }

    public void setBase (String base)  {
        this.base = base;
    }

    public Wind getWind ()  {
        return wind;
    }

    public void setWind (Wind wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "ClassPojo [visibility = "+visibility+", timezone = "+timezone+", main = "+main+", clouds = "+clouds+", sys = "+sys+", dt = "+dt+", coord = "+coord+", weather = "+weather+", name = "+name+", cod = "+cod+", id = "+id+", base = "+base+", wind = "+wind+"]";
    }
}
