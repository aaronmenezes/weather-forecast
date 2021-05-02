package com.kyser.weatherforecast.services;

import android.util.Log;

import com.kyser.weatherforecast.model.CurrentModel;
import com.kyser.weatherforecast.model.Forecast;
import com.kyser.weatherforecast.model.Weather;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherService {

    private final String APIKEY = "e122df3803bdbf78640b54ddb91d7440";
    private static WeatherService instance;
    private Retrofit mRetrofitInstance;
    private ForecastService mForecastService;

    private WeatherService()  {}

    public static  WeatherService getInstance(){
        if(instance == null) {
            instance = new WeatherService();
            instance.initService();
        }
        return instance;
    }


    private void initService() {
        mRetrofitInstance =new  Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mForecastService = mRetrofitInstance.create(ForecastService.class);
    }

    public void  getWeatherService(String city, final MutableLiveData<CurrentModel> model){
        mForecastService.getCurrentWeather(city,APIKEY,"metric").enqueue(new Callback<CurrentModel>() {
            @Override
            public void onResponse(Call<CurrentModel> call, Response<CurrentModel> response) {
                model.setValue(response.body());
                Log.v("WS|currentWeather", response.raw().toString());
            }

            @Override
            public void onFailure(Call<CurrentModel> call, Throwable t) {
                Log.v("WeatherService",t.getLocalizedMessage());
            }
        });
    }

    public void  getForecast(Double latitude, Double longitude, final MutableLiveData<Forecast> model) {
        mForecastService.getForecast(latitude, longitude ,APIKEY,"minutely,current","metric").enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                model.setValue(response.body());
                Log.v("WS|getForecast", response.raw().toString());
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.v("WeatherService",t.getLocalizedMessage());
            }
        });
    }
}
