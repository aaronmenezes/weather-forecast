package com.kyser.weatherforecast.ui.viewmodel;

import android.location.Location;

import com.kyser.weatherforecast.model.CurrentModel;
import com.kyser.weatherforecast.model.Forecast;
import com.kyser.weatherforecast.services.WeatherService;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CurrentWeather extends ViewModel {

    private String mCityname = "London";
    private Double mLongitude = 51.5074;
    private Double mLatitude = 0.1278;
    MutableLiveData<Forecast> mForecastObservable;
    MutableLiveData<CurrentModel> mCurrentWeatherObservable;

    public CurrentWeather() {
        mCurrentWeatherObservable = new MutableLiveData<>();
        mForecastObservable = new MutableLiveData<>();
     //   getCurrentCondition();
      //  getForecast();
    }

    public void updateCityInfo(String cityname,Location location){
        mCityname = cityname;
        mLongitude = location.getLongitude();
        mLatitude = location.getLatitude();
        getCurrentCondition();
        getForecast();
    }

    private void getCurrentCondition(){
        WeatherService.getInstance().getWeatherService(mCityname,mCurrentWeatherObservable);
    }

    private void getForecast(){
       WeatherService.getInstance().getForecast(mLatitude,mLongitude,mForecastObservable);
    }

    public MutableLiveData<Forecast> getmForecastObservable() {
        return mForecastObservable;
    }

    public MutableLiveData<CurrentModel> getmCurrentWeatherObservable() {
        return mCurrentWeatherObservable;
    }
}
