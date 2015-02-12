package com.cuckoo.cuckooweather.activity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cuckoo.cuckooweather.R;
import com.cuckoo.cuckooweather.network.WeatherManagerHandle;
import com.cuckoo.cuckooweather.util.BaiduLocation;
import com.cuckoo.cuckooweather.util.BaiduLocationListener;
import com.cuckoo.cuckooweather.util.Constant;
import com.cuckoo.cuckooweather.util.WeatherImagesHelper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.suckoo.cuckoo.weather.model.LifeIndex;
import com.suckoo.cuckoo.weather.model.Weather;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

public class WeatherFragment extends Fragment {

	private TextView location_tv = null;
    private TextView weatherInfo_tv = null;

    //天气
    private TextView weather_tv = null;
    private TextView realtime_temperature_tv = null;
    private TextView wind_tv = null;
    private TextView date_tv = null;
    private TextView temperature_tv = null;
    private WeatherImagesHelper wih = null;
    private GridView gridView;
  //初始化首选项保存对象
	private SharedPreferences mySharedPreferences = null;
	private SharedPreferences.Editor editor = null;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_weather, container, false);
		
		location_tv = (TextView)view.findViewById(R.id.location_tv);
//      weatherInfo_tv = (TextView)view.findViewById(R.id.weatherInfo_tv);
		
		//首选项保存
		mySharedPreferences = getActivity().getSharedPreferences(Constant.MY_PREFS, Activity.MODE_PRIVATE);
		editor = mySharedPreferences.edit();
		
      //天气
      realtime_temperature_tv = (TextView)view.findViewById(R.id.tv_realtime_temperature);
      weather_tv = (TextView)view.findViewById(R.id.weather_tv);
      wind_tv = (TextView)view.findViewById(R.id.wind_tv);
      date_tv = (TextView)view.findViewById(R.id.date_tv);
      temperature_tv =  (TextView)view.findViewById(R.id.temperature_tv);

      wih = new WeatherImagesHelper(getActivity());
      gridView = (GridView)view.findViewById(R.id.weatherforecast_gv);

		BaiduLocation location =  new BaiduLocation();
		location.onCreate(getActivity());
		location.setOnBaiduLocationListener(new BaiduLocationListener() {
			@Override
			public void onLocationSuccess(String city) {
				//定位成功
				location_tv.setText(city);
				
				//判断是否已经查找过天气以及是否过期
				Boolean haveWeatherInfo = mySharedPreferences.getString(Constant.PR_KEY_WEATHER_INFO, "1").equals("1");
				Boolean isExp = isExpired();//判断是否过期
				
				if(haveWeatherInfo && isExp) {
					getWeatherInfo(city);
				} else {
					//加载上次的天气，更新界面
					Weather lastWeather = new Weather();
					lastWeather.setTemperature(mySharedPreferences.getString(Constant.PR_KEY_TEMP, "12˚~18˚C"));
					lastWeather.setWeatherInfo(mySharedPreferences.getString(Constant.PR_KEY_WEATHER_INFO, "晴"));
					lastWeather.setWind(mySharedPreferences.getString(Constant.PR_KEY_WIND, "微风"));
					lastWeather.setDataString(mySharedPreferences.getString(Constant.PR_KEY_DATA, "2014-12-30"));
					
					List<Weather> forecastList = new ArrayList<Weather>(); 
	                //解析预报
	                for(int i = 1;i < 4; i ++) {
	                 String forecastWeatherString = mySharedPreferences.getString(Constant.PR_KEY_FORECAST+i, "");
	                 JSONObject jsonObject = null;
					try {
						jsonObject = new JSONObject(forecastWeatherString);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	                 Weather weatherObj = Weather.parseResponseJSON(jsonObject);
	                 
	                 //设置图片资源
	                 String imgIndex = wih.searchImageRes(weatherObj.getWeatherInfo());
	                 Resources res = getResources();
	                 int imgId = res.getIdentifier("w"+imgIndex, "drawable", getActivity().getPackageName());
	                 weatherObj.setDayImgRes(imgId); 
	                 
	                 forecastList.add(weatherObj);
	                } 
	                
					lastWeather.setForecastWeathers(forecastList);
					
					updateView(lastWeather);
				}
			}
		});
		
		return view;
	}
	
	//获取天气信息
	public void getWeatherInfo(String city) {
		WeatherManagerHandle weatherManagerHandle = new WeatherManagerHandle();
		weatherManagerHandle.getWeatherInfo(city, new JsonHttpResponseHandler() {

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				super.onFailure(statusCode, headers, responseString, throwable);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				
				
				JSONArray weatherArray;
				try {
					JSONArray results = response.getJSONArray("results");
					System.out.println(response.toString());
					
					JSONObject data = results.getJSONObject(0);
					weatherArray = data.getJSONArray("weather_data");
	                JSONObject todayWeatherJsonObject = weatherArray.getJSONObject(0);
	                Weather weather = new Weather();
	                weather = Weather.parseResponseJSON(todayWeatherJsonObject);
	                
	                List<Weather> forecastList = new ArrayList<Weather>(); 
	                //解析预报
	                for(int i = 1;i < weatherArray.length(); i ++) {
	                 Weather weatherObj = Weather.parseResponseJSON(weatherArray.getJSONObject(i));
	                 
	                 editor.putString(Constant.PR_KEY_FORECAST+i, weatherArray.getJSONObject(i).toString());
	                 //设置图片资源
	                 String imgIndex = wih.searchImageRes(weatherObj.getWeatherInfo());
	                 Resources res = getResources();
	                 int imgId = res.getIdentifier("w"+imgIndex, "drawable", getActivity().getPackageName());
	                 weatherObj.setDayImgRes(imgId); 
	                 
	                 forecastList.add(weatherObj);
	                }
	                
	                List<LifeIndex> indexs = new ArrayList<LifeIndex>();
	                
	                JSONArray indexArray = data.getJSONArray("index");
	                for(int i = 0;i < indexArray.length(); i++) {
	                	LifeIndex index = LifeIndex.parseJson(indexArray.getJSONObject(i));
	                	
	                	editor.putString(Constant.PR_KEY_INDEX+i, indexArray.getJSONObject(i).toString());
	                	indexs.add(index);
	                }
	                
	                weather.setForecastWeathers(forecastList);
	                weather.setIndexs(indexs);
	                
	                //PM2.5
	                String pm25 = data.getString("pm25");
	                weather.setPM25(pm25);
	                //city
	                String city =  data.getString("currentCity");
	                weather.setCity(city);
	                
	                //保存数据
	                editor.putString(Constant.PR_KEY_WEATHER_INFO, weather.getWeatherInfo());
	        		editor.putString(Constant.PR_KEY_CITY, weather.getCity());
	        		editor.putString(Constant.PR_KEY_WIND, weather.getWind());
	                editor.putString(Constant.PR_KEY_DATA, weather.getDataString());
	                editor.putString(Constant.PR_KEY_TEMP, weather.getTemperature());
	                
	                //获取当前时间
	                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        		String curTimeString = df.format(new Date());
	        		
	                editor.putString(Constant.PR_PUTDATE, curTimeString);
	                editor.apply();
	                
	                //更新界面
	                updateView(weather);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	public Boolean isExpired() {
		String lastTimeString = mySharedPreferences.getString(Constant.PR_PUTDATE, "0");
		
		if (lastTimeString.equals("0")) {
			return true;
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date lastDate = null;		
		try {
			lastDate = df.parse(lastTimeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date curDate = new Date();
		
		long diff = curDate.getTime() - lastDate.getTime();
		long day=diff/(24*60*60*1000);
		long hour=(diff/(60*60*1000)-day*24);
		
		if (day > 0) {
			return true;
		} 
		
		System.out.println(""+day+"天"+hour+"小时");

		//判断是否超过1个小时
		if ((day == 0) && hour > 1 ) {
			return true;
		} else {
			return false;
		}		
	}
	
	//更新界面
	public void updateView(Weather weather) {
		weather_tv.setText(weather.getWeatherInfo());
		realtime_temperature_tv.setText(weather.getDataString().split("：")[1].split("℃")[0]+"˚");
        temperature_tv.setText(weather.getTemperature());
        wind_tv.setText(weather.getWind());
        date_tv.setText(weather.getDataString().substring(0, 10));
        
        updateGridView((ArrayList<Weather>)weather.getForecastWeathers());
        
	}
	
	public void updateGridView(ArrayList<Weather> weathers) {
        gridView.setAdapter(new WeatherForecastAdapter(weathers, getActivity()));
    }
}
