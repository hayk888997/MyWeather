package com.french.egs.haykn.myweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.french.egs.haykn.myweather.adapters.ListViewDailyWeatherAdapter;
import com.french.egs.haykn.myweather.api.ApiClient;
import com.french.egs.haykn.myweather.api.ApiManager;
import com.french.egs.haykn.myweather.model.CityDetailsModel;
import com.french.egs.haykn.myweather.model.DailyWeatherDetails;
import com.french.egs.haykn.myweather.utils.Constants;
import com.french.egs.haykn.myweather.utils.Util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class WeatherDetailsActivity extends AppCompatActivity {

    private CityDetailsModel cityDetailsModel;
    private final ApiClient client = ApiManager.getWeatherClient();

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
        //Refresh data every time when calls activities onCreate() method (device rotation included of course). I kept it cause didn't know what will be better for you :)
        getWeatherData();
    }


    private void getWeatherData() {
        client.getWeatherByCityName(Constants.CITY_NAME, new Callback<CityDetailsModel>() {
            @Override
            public void success(CityDetailsModel weather, Response response) {
                cityDetailsModel = weather;
                setData();
            }

            @Override
            public void failure(RetrofitError error) {
                String message = error.getMessage();
                Toast.makeText(WeatherDetailsActivity.this, message, Toast.LENGTH_LONG).show(); //TODO:Can be ToastManager in the future
            }
        });

        client.getDailyWeather(Constants.CITY_NAME, Constants.DAYS_COUNT_FOR_GETTING_WEATHER, new Callback<DailyWeatherDetails>() {
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
        try {

            //Initializing views
            ((TextView) findViewById(R.id.txtViewCityName)).setText(Constants.CITY_NAME);
            ((TextView) findViewById(R.id.txtViewWeatherDescription)).setText(cityDetailsModel.getWeatherList().get(0).getDescription());
            ((TextView) findViewById(R.id.txtViewTemp)).setText(String.valueOf(new DecimalFormat("##.##").format((Util.fahrenheitToCelsius(cityDetailsModel.getMain().getTemp())))));

            ((TextView) findViewById(R.id.txtViewSunrise)).setText(getString(R.string.sunrise) + Util.milsToHour(cityDetailsModel.getSys().getSunrise()));
            ((TextView) findViewById(R.id.txtViewSunset)).setText(getString(R.string.sunset) + Util.milsToHour(cityDetailsModel.getSys().getSunset()));

            ((TextView) findViewById(R.id.txtViewClouds)).setText(getString(R.string.clouds) + cityDetailsModel.getClouds().getCloudPercentage());

            if (cityDetailsModel.getRain() != null) {
                ((TextView) findViewById(R.id.txtViewRain)).setText(getString(R.string.rain) + cityDetailsModel.getRain().getRainInfoInMM());
            } else {
                ((TextView) findViewById(R.id.txtViewRain)).setText(getString(R.string.rain) + getString(R.string.no_info));
            }
            ((TextView) findViewById(R.id.txtViewHumidity)).setText(getString(R.string.humidity) + cityDetailsModel.getMain().getHumidity());
            ((TextView) findViewById(R.id.txtViewPressure)).setText(getString(R.string.pressure) + cityDetailsModel.getMain().getPressure());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
