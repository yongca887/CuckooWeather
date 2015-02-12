package com.suckoo.cuckoo.weather.model;

import java.util.List;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
	private String id;
	private String temperature;
	private String dataString;
	private int dayImgRes;
	private int nightImgRes;
	private String wind;
	private String city;
	private String PM25;
	private String weatherInfo;
	private List<LifeIndex> indexs;
	private List<Weather> forecastWeathers;
	
	
	public int getDayImgRes() {
		return dayImgRes;
	}
	public void setDayImgRes(int dayImgRes) {
		this.dayImgRes = dayImgRes;
	}
	public int getNightImgRes() {
		return nightImgRes;
	}
	public void setNightImgRes(int nightImgRes) {
		this.nightImgRes = nightImgRes;
	}
	public String getPM25() {
		return PM25;
	}
	public void setPM25(String pM25) {
		PM25 = pM25;
	}
	public List<LifeIndex> getIndexs() {
		return indexs;
	}
	public void setIndexs(List<LifeIndex> indexs) {
		this.indexs = indexs;
	}
	public List<Weather> getForecastWeathers() {
		return forecastWeathers;
	}
	public void setForecastWeathers(List<Weather> forecastWeathers) {
		this.forecastWeathers = forecastWeathers;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getDataString() {
		return dataString;
	}
	public void setDataString(String dataString) {
		this.dataString = dataString;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWeatherInfo() {
		return weatherInfo;
	}
	public void setWeatherInfo(String weatherInfo) {
		this.weatherInfo = weatherInfo;
	}
	
	
	
	@Override
	public String toString() {
		return "Weather [id=" + id + ", temperature=" + temperature
				+ ", dataString=" + dataString + ", wind=" + wind + ", city="
				+ city + ", weatherInfo=" + weatherInfo + ", indexs=" + indexs
				+ ", forecastWeathers=" + forecastWeathers + "]";
	}
	
	public static Weather parseResponseJSON(JSONObject json) {
		Weather weather = new Weather();
		try {
			weather.weatherInfo = json.get("weather").toString();
			weather.dataString = json.get("date").toString();
			weather.wind = json.get("wind").toString();
			weather.temperature = json.get("temperature").toString();		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return weather;
	}
}
