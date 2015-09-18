package com.french.egs.haykn.myweather.api.model;

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
            private double day;
            private double night;


            public double getDay() {
                return day;
            }

            public void setDay(double day) {
                this.day = day;
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
