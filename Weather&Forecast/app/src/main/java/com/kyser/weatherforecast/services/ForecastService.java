package com.kyser.weatherforecast.services;

import com.kyser.weatherforecast.model.CurrentModel;
import com.kyser.weatherforecast.model.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForecastService {

    @GET("data/2.5/weather")
    Call<CurrentModel> getCurrentWeather (@Query("q")String city, @Query("appid")String apikey ,@Query("units")String units);

    @GET("data/2.5/onecall")
    Call<Forecast> getForecast (@Query("lat")Double lat, @Query("lon")Double lon, @Query("appid")String apikey, @Query("exclude")String exclude, @Query("units")String units);
}
