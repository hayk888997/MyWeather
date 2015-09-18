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
import com.french.egs.haykn.myweather.model.DailyWeatherDetails;
import com.french.egs.haykn.myweather.utils.Util;

import java.text.DecimalFormat;
import java.util.List;


public class ListViewDailyWeatherAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<DailyWeatherDetails.ListItem> mData;

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


        ((TextView) convertView.findViewById(R.id.textViewDayName)).setText(Util.getWeekShortDay(item.getDate()));

        ((TextView) convertView.findViewById(R.id.textViewTemperatureFrom)).
                setText(String.valueOf(new DecimalFormat("##.##").format
                        ((Util.fahrenheitToCelsius(item.getTemp().getDayTemp())))));

        TextView temperatureTo = (TextView) convertView.findViewById(R.id.textViewTemperatureTo);
        temperatureTo.setText(String.valueOf (new DecimalFormat("##.##").format((item.getTemp().getNight()))));

        return convertView;
    }
}