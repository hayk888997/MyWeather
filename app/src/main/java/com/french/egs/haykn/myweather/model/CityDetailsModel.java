package com.french.egs.haykn.myweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by haykn on 9/18/15.
 */
public class CityDetailsModel {

    private Sys sys;

    @SerializedName("weather")
    private List<Weather> weatherList;

    private Main main;
    private Rain rain;
    private Cloud clouds;

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public Cloud getClouds() {
        return clouds;
    }

    public void setClouds(Cloud clouds) {
        this.clouds = clouds;
    }

    public class Rain {
        @SerializedName("1h")
        private double rainInfoInMM;

        public double getRainInfoInMM() {
            return rainInfoInMM;
        }

        public void setRainInfoInMM(double rainInfoInMM) {
            this.rainInfoInMM = rainInfoInMM;
        }
    }

    public class Weather {
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    public class Main {

        private double humidity;
        private double pressure;
        private double temp;

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

    }

    public class Sys {

        private long sunrise;
        private long sunset;

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }
    }

    public class Cloud {
        @SerializedName("all")
        private double cloudPercentage;

        public double getCloudPercentage() {
            return cloudPercentage;
        }

        public void setCloudPersantage(double cloudePersantage) {
            this.cloudPercentage = cloudePersantage;
        }
    }
}
