package com.kyser.weatherforecast.services;

import android.util.Log;

import com.kyser.weatherforecast.BuildConfig;
import com.kyser.weatherforecast.model.wallpaper.ApiResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WallpaperService {
    private String APIKEY = BuildConfig.WALLPAPER_APIKEY;
    private static WallpaperService serivce;
    private Retrofit mRetrofitInstance;
    private CityWallpaperService mCityWallpaperService;

    private WallpaperService(){}

    public interface WallpaperResponse{
        void onReady(String url );
    }
    public static WallpaperService getInstance(){
        if(serivce == null) {
            serivce = new WallpaperService();
            serivce.initService();
        }
        return serivce;
    }
    private void initService() {
        mRetrofitInstance =new  Retrofit.Builder()
                .baseUrl("https://api.unsplash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mCityWallpaperService = mRetrofitInstance.create(CityWallpaperService.class);
    }
    public void  getCityWallpaper(String city, final WallpaperResponse wallpaperResponse ){
        mCityWallpaperService.getCityWallpaper(APIKEY,"1",city).enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                int index = (int) ((Math.random()% (response.body().getResults().size()))*10);
                Log.v(" WallpaperService ", response.body().getResults().get(index).getUrls().getRaw());
                wallpaperResponse.onReady(response.body().getResults().get(index).getUrls().getRaw());
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.v("WallpaperService",t.getLocalizedMessage());
            }
        });
    }
}
