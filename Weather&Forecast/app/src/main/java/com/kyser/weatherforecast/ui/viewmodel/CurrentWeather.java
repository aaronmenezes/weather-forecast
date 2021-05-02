package com.kyser.weatherforecast.ui.viewmodel;

import com.kyser.weatherforecast.model.CurrentModel;
import com.kyser.weatherforecast.model.Forecast;
import com.kyser.weatherforecast.services.WeatherService;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CurrentWeather extends ViewModel {

    private String mCityname = "Mumbai";
    private Double mLongitude = 72.8488953;
    private Double mLatitude = 19.2510066;
    MutableLiveData<Forecast> mForecastObservable;
    MutableLiveData<CurrentModel> mCurrentWeatherObservable;

    public CurrentWeather() {
        mCurrentWeatherObservable = new MutableLiveData<>();
        mForecastObservable = new MutableLiveData<>();
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
