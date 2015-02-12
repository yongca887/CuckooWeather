package com.cuckoo.cuckooweather.network;

import java.util.Map;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpRequestHelper {
	private static HttpRequestHelper instanceRequestHelper = null;
	
	private HttpRequestHelper() {
		
	}
	
	public static HttpRequestHelper DefaultHttpRequestHelper() {
		if (instanceRequestHelper == null) {
			instanceRequestHelper = new HttpRequestHelper();
		}
		
		return instanceRequestHelper;	
	}
	
	public void requestWithURL(String url, Map<String, String> paramsMap, JsonHttpResponseHandler responseHandler) {
        AsyncHttpClient client = new AsyncHttpClient();
        
        RequestParams params = new RequestParams();

        for(String key : paramsMap.keySet()) {
        	String value = paramsMap.get(key);
        	params.put(key, value);
        }
           
        client.post(url, params, responseHandler);
        
	}
}
