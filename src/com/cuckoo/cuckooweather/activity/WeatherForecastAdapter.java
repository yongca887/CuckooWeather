package com.cuckoo.cuckooweather.activity;

import java.util.ArrayList;

import com.cuckoo.cuckooweather.R;
import com.cuckoo.cuckooweather.util.WeatherImagesHelper;
import com.suckoo.cuckoo.weather.model.Weather;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherForecastAdapter extends BaseAdapter {
	public ArrayList<Weather> weatherArrayList;
    public LayoutInflater mInflater;
    private Context _context = null;
    
    public WeatherForecastAdapter(ArrayList<Weather> weathers, Context context) {
    	_context = context;
        weatherArrayList = weathers;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return weatherArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return weatherArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            View itemView = mInflater.inflate(R.layout.weather_item_layout, null);
            Weather weather = weatherArrayList.get(position);

            ImageView image = (ImageView)itemView.findViewById(R.id.weather_img);
            Drawable imageDrawable = _context.getResources().getDrawable(weather.getDayImgRes());
            image.setImageDrawable(imageDrawable);
//            image.setImageResource(R.drawable.w0);
            
            String dateString = weather.getDataString();
            String weekday = dateString.substring(0,2);
            TextView weekday_tv = (TextView)itemView.findViewById(R.id.weekday_tv);
            weekday_tv.setText(weekday);

            String temperature = weather.getTemperature();
            String temperature_top = temperature.split("~")[0];
            String temperature_bottom = temperature.split("~")[1];
            TextView temp_top_tv = (TextView)itemView.findViewById(R.id.temperature_top_tv);
            temp_top_tv.setText(temperature_top);
            TextView temp_bottom_tv = (TextView)itemView.findViewById(R.id.temperature_bottom_tv);
            temp_bottom_tv.setText(temperature_bottom);
            
            TextView weatherInfo_tv = (TextView)itemView.findViewById(R.id.weather_bottom_tv);
            weatherInfo_tv.setText(weather.getWeatherInfo());

            return itemView;
        } else  {
            Weather weather = weatherArrayList.get(position);

            ImageView image = (ImageView)convertView.findViewById(R.id.weather_img);
            Drawable imageDrawable = _context.getResources().getDrawable(weather.getDayImgRes());
            image.setImageDrawable(imageDrawable);
//            image.setImageResource(R.drawable.w0);

            String dateString = weather.getDataString();
            String weekday = dateString.substring(0,2);
            TextView weekday_tv = (TextView)convertView.findViewById(R.id.weekday_tv);
            weekday_tv.setText(weekday);

            String temperature = weather.getTemperature();
            String temperature_top = temperature.split("~")[0];
            String temperature_bottom = temperature.split("~")[1];
            TextView temp_top_tv = (TextView)convertView.findViewById(R.id.temperature_top_tv);
            temp_top_tv.setText(temperature_top);
            TextView temp_bottom_tv = (TextView)convertView.findViewById(R.id.temperature_bottom_tv);
            temp_bottom_tv.setText(temperature_bottom);
            TextView weatherInfo_tv = (TextView)convertView.findViewById(R.id.weather_bottom_tv);
            weatherInfo_tv.setText(weather.getWeatherInfo());

            return convertView;
        }
	}

}
