package com.french.egs.haykn.myweather.api;

import com.french.egs.haykn.myweather.utils.Constants;

import retrofit.RestAdapter;

/**
 * Created by haykn on 9/18/15.
 */
public class ApiManager {

    private static ApiClient apiClient = null;

    public static ApiClient getWeatherClient() {
        if (apiClient == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(Constants.BASE_URL)
                    .build();

            apiClient = restAdapter.create(ApiClient.class);
        }
        return apiClient;
    }

}
