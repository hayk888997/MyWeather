package com.french.egs.haykn.myweather;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.french.egs.haykn.myweather.adapters.ListViewDailyWeatherAdapter;
import com.french.egs.haykn.myweather.api.ApiClient;
import com.french.egs.haykn.myweather.api.ApiManager;
import com.french.egs.haykn.myweather.api.model.CityDetails;
import com.french.egs.haykn.myweather.api.model.DailyWeatherDetails;
import com.french.egs.haykn.myweather.utils.Constants;
import com.french.egs.haykn.myweather.utils.Util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class WeatherDetailsActivity extends Activity {

    private CityDetails cityDetails;
    private ApiClient client = ApiManager.getWeatherClient();

    private ListViewDailyWeatherAdapter adapter;
    private List<DailyWeatherDetails.ListItem> listOfDailyWeathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        listOfDailyWeathers = new ArrayList<>();

        ListView listView = (ListView) findViewById(R.id.listViewDailyWeather);
        adapter = new ListViewDailyWeatherAdapter(WeatherDetailsActivity.this, listOfDailyWeathers);
        listView.setAdapter(adapter);

        getData();
    }

    private void getData() {
        client.getWeatherByCityName(Constants.CITY_NAME, new Callback<CityDetails>() {
            @Override
            public void success(CityDetails weather, Response response) {
                cityDetails = weather;
                setData();
            }

            @Override
            public void failure(RetrofitError error) {
                String message = error.getMessage();
                Toast.makeText(WeatherDetailsActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        client.getDailyWeather(Constants.CITY_NAME, Constants.daysCountForWeather, new Callback<DailyWeatherDetails>() {
            @Override
            public void success(DailyWeatherDetails dailyWeatherDet, Response response) {
                listOfDailyWeathers.clear();
                listOfDailyWeathers.addAll(dailyWeatherDet.getItemList());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                String message = error.getMessage();
                Toast.makeText(WeatherDetailsActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setData() {

        ((TextView)findViewById(R.id.txtViewCityName)).setText(Constants.CITY_NAME);
        ((TextView)findViewById(R.id.txtViewWeatherDescription)).setText(cityDetails.getWeatherList().get(0).getDescription());
        ((TextView)findViewById(R.id.txtViewTemp)).setText(String.valueOf(new DecimalFormat("##.##").format((Util.fahrenheitToCelsius(cityDetails.getMain().getTemp())))));

        ((TextView)findViewById(R.id.txtViewSunrise)).setText(getString(R.string.sunrise) + milsToHour(cityDetails.getSys().getSunrise()));
        ((TextView)findViewById(R.id.txtViewSunset)).setText(getString(R.string.sunset) + milsToHour(cityDetails.getSys().getSunset()));

        ((TextView)findViewById(R.id.txtViewClouds)).setText(getString(R.string.clouds) + cityDetails.getClouds().getCloudePersantage());

        if(cityDetails.getRain() != null) {
            ((TextView)findViewById(R.id.txtViewRain)).setText(getString(R.string.rain) + cityDetails.getRain().getRain());
        } else {
            ((TextView)findViewById(R.id.txtViewRain)).setText(getString(R.string.rain) + getString(R.string.no_info));
        }
        ((TextView)findViewById(R.id.txtViewHumidity)).setText(getString(R.string.humidity) + cityDetails.getMain().getHumidity());
        ((TextView)findViewById(R.id.txtViewPressure)).setText(getString(R.string.pressure) + cityDetails.getMain().getPressure());






    }

    String milsToHour(long mils) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mils);
        String am_pm;
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        if(calendar.get(Calendar.AM_PM) == 0) {
            am_pm = "AM";
        } else {
            am_pm = "PM";
        }
        String time= hour + ":" + minute + " " + am_pm;
        return time;

    }


    public void onClick(View view) {


    }

    public void onClick2(View view) {
        ApiClient client = ApiManager.getWeatherClient();


    }
}
