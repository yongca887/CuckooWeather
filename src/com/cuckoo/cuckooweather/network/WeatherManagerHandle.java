package com.cuckoo.cuckooweather.network;

import java.util.HashMap;

import com.loopj.android.http.JsonHttpResponseHandler;

public class WeatherManagerHandle extends BaseHandle {
	public void getWeatherInfo(String city, JsonHttpResponseHandler httpResponseHandler) {
				//登录请求
				HttpRequestHelper requestHelper = HttpRequestHelper.DefaultHttpRequestHelper();
				//请求地址
			    String url = APIconfig.API_WEATHER;
			    //请求参数
			    HashMap<String, String> paramsMap = new HashMap<String, String>();
			    
			    paramsMap.put("location", city);
			    paramsMap.put("output", "json");
			    paramsMap.put("ak", "5lUMgoGrSKLav7Z8445Y12By");
			    
				requestHelper.requestWithURL(url,paramsMap, httpResponseHandler);
	}
}
