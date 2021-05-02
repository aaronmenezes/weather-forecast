package com.kyser.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

public class Coordinates {

    @SerializedName("lon")
    private float lon;
    @SerializedName("lat")
    private float lat;
}
