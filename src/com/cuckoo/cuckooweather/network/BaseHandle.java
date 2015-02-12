package com.cuckoo.cuckooweather.network;

public class BaseHandle {
	private static String HOST = "http://192.168.199.234";
//	private static String HOST = "http://192.168.43.80";
//	private static String HOST = "http://172.20.10.3";

	public static String requestUrlWithPath(String apiurl) { 	
		
		return HOST+apiurl;
	}
}
