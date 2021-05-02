package com.kyser.weatherforecast.services;

import com.kyser.weatherforecast.model.wallpaper.ApiResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CityWallpaperService {

    @GET("/search/photos/")
    Call<ApiResult> getCityWallpaper (@Query("client_id")String client_id, @Query("page")String page, @Query("query")String query);
}
