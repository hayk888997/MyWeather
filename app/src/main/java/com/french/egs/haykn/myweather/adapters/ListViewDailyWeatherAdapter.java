package com.french.egs.haykn.myweather.adapters;

/**
 * Created by haykn on 9/18/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.french.egs.haykn.myweather.R;
import com.french.egs.haykn.myweather.api.model.DailyWeatherDetails;
import com.french.egs.haykn.myweather.utils.Util;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;



public class ListViewDailyWeatherAdapter extends BaseAdapter {
    private Context mContext;
    private List<DailyWeatherDetails.ListItem> mData;

    public ListViewDailyWeatherAdapter(Context context, List<DailyWeatherDetails.ListItem> data){ //TODO: change the names
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public DailyWeatherDetails.ListItem getItem(int position) {
        if (mData != null) {
            mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_day_item, parent, false);
        }
        DailyWeatherDetails.ListItem item = mData.get(position);

        TextView dayName = (TextView) convertView.findViewById(R.id.textViewDayName);

//        Date date = new Date(item.getDate());
//        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
//        dayName.setText(String.valueOf(simpleDateformat.format(date)));

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date(item.getDate()));
        int dow = cal.get(Calendar.DAY_OF_WEEK);

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] weekdays = dfs.getShortWeekdays();
        dayName.setText(weekdays[dow]);
        System.out.println(weekdays[dow]);


        TextView temperatureFrom = (TextView) convertView.findViewById(R.id.textViewTemperatureFrom);
        temperatureFrom.setText(String.valueOf(new DecimalFormat("##.##").format((Util.fahrenheitToCelsius(item.getTemp().getDay())))));

        TextView temperatureTo = (TextView) convertView.findViewById(R.id.textViewTemperatureTo);
        temperatureTo.setText(String.valueOf (new DecimalFormat("##.##").format((item.getTemp().getNight()))));

        return convertView;
    }
}