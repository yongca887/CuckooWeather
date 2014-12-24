package com.cuckoo.cuckooweather.network;

public class BaseHandle {
	private static String HOST = "http://127.0.0.1";
	public static String requestUrlWithPath(String apiurl) { 	
		
		return HOST+apiurl;
	}
}
