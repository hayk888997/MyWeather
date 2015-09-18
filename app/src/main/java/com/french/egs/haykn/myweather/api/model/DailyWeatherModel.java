package com.french.egs.haykn.myweather.api.model;

/**
 * Created by haykn on 9/18/15.
 */
public class DailyWeatherModel {

    private String dayName;
    private double temperatureFrom;
    private double temperatureTo;

    public DailyWeatherModel(String nameOfDay, double tempFrom, double tempTo){
        dayName = nameOfDay;
        temperatureFrom = tempFrom;
        temperatureTo = tempTo;
    }

    public String getDayName() {
        return dayName;
    }

    public double getTemperatureFrom() {
        return temperatureFrom;
    }

    public double getTemperatureTo() {
        return temperatureTo;
    }

}
