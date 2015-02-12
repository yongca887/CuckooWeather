package com.cuckoo.cuckooweather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.util.Log;

public class WeatherImagesHelper {
	AssetManager assets = null;
	Context context = null;
	
	public WeatherImagesHelper(Context _context) {
		context = _context;
	}
	
	public String searchImageRes(String searchKey) {
		String resultString = null;
		
		assets = context.getAssets();
		InputStream inputStream = null;
		
		try {
			inputStream = assets.open("weather_correction.json");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {			
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while((line = bufferedReader.readLine()) != null) {				
				stringBuilder.append(line);
			}
			
			bufferedReader.close();			
			inputStreamReader.close();	
			
			JSONObject jsonObject = new JSONObject(stringBuilder.toString());			
			JSONArray jsonArray = jsonObject.getJSONArray("values");
			for (int i = 0; i < jsonArray.length(); i++) {				
				JSONObject object = jsonArray.getJSONObject(i);				
//				Log.i("TESTJSON", "----------------");				
//				Log.i("TESTJSON", "id=" + object.getString("imgid"));				
//				Log.i("TESTJSON", "name=" + object.getString("weathername"));	
				String weather = object.getString("weathername");
				if (searchKey.equals(weather)) {
					return object.getString("imgid");
				}
			}		
			} catch (UnsupportedEncodingException e) {			
				e.printStackTrace();		
			} catch (IOException e) {
					e.printStackTrace();		
			} catch (JSONException e) {			
				e.printStackTrace();		
			}
		
		if (resultString == null) {
			resultString = "1";
		}
		
		return resultString;
	}
}
