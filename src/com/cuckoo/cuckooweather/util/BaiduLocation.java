package com.cuckoo.cuckooweather.util;

import android.app.Activity;
import android.app.Application;
import android.widget.TabHost;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class BaiduLocation {
	public TabHost tabHost;
	public LocationClient mLocationClient = null;
	public Activity activity;
	public String city;
	public BDLocationListener myListener = null;
	private BaiduLocationListener locationListener = null;
	 
	public void onCreate(Activity activity) {
	    this.activity=activity;
		mLocationClient = new LocationClient(activity.getApplicationContext());     //LocationClient
	    LocationClientOption option = new LocationClientOption();
	    option.setOpenGps(true);
	    option.setLocationMode(LocationMode.Hight_Accuracy);//
	    option.setCoorType("bd09ll");//
	    option.setIsNeedAddress(true);//
	    mLocationClient.setLocOption(option);  
	    
	    myListener = new MyLocationListener();
	    mLocationClient.registerLocationListener( myListener );    //
	    mLocationClient.start();
	}
	
	public void setOnBaiduLocationListener(BaiduLocationListener _locationListener) {
		locationListener = _locationListener;
	}
	
	class MyLocationListener implements BDLocationListener{

		@Override
		 public void onReceiveLocation(BDLocation location) {
			  if (location == null)
			   return ;		  	
			city=(location.getCity());
			locationListener.onLocationSuccess(city);
			mLocationClient.stop();
		} 		 
	}	
}
