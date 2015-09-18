package com.french.egs.haykn.myweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haykn on 9/18/15.
 */
public class DailyWeatherDetails {

    @SerializedName("list")
    private List<ListItem> itemList;

    public DailyWeatherDetails() {
        itemList = new ArrayList<>();
    }

    public List<ListItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<ListItem> itemList) {
        this.itemList = itemList;
    }

    public class ListItem {

        @SerializedName("dt")
        private long date;

        private Temp temp;

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public class Temp {

            @SerializedName("day")
            private double dayTemp;
            private double night;


            public double getDayTemp() {
                return dayTemp;
            }

            public void setDayTemp(double dayTemp) {
                this.dayTemp = dayTemp;
            }

            public double getNight() {
                return night;
            }

            public void setNight(double night) {
                this.night = night;
            }
        }
    }
}
