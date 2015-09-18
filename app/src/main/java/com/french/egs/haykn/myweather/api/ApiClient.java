package com.french.egs.haykn.myweather.api;

import com.french.egs.haykn.myweather.api.model.CityDetails;
import com.french.egs.haykn.myweather.api.model.DailyWeatherDetails;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by haykn on 9/18/15.
 */

public interface ApiClient {

    @GET("/weather")
    void getWeatherByCityName(@Query("q") String city, Callback<CityDetails> currentWeather);

    @GET("/forecast/daily")
    void getDailyWeather(@Query("q") String city, @Query("cnt") Integer count, Callback<DailyWeatherDetails> currentWeather);

}
