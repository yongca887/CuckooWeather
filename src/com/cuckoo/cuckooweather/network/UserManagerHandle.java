package com.cuckoo.cuckooweather.network;

import java.util.HashMap;

import org.apache.http.Header;
import org.json.JSONArray;

import com.cuckoo.cuckooweather.util.SecurityUtil;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.suckoo.cuckoo.weather.model.User;

public class UserManagerHandle {
	// 登录
	public void login(User user, JsonHttpResponseHandler httpResponseHandler) {
		// 登录请求
		HttpRequestHelper requestHelper = HttpRequestHelper
				.DefaultHttpRequestHelper();
		// 请求地址
		String url = BaseHandle.requestUrlWithPath(APIconfig.API_LOGIN);
		// 请求参数
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(User.KEY_NAME, user.getUsername());
		paramsMap.put(User.KEY_PASSWORD, SecurityUtil.md5(user.getPassword()));

		requestHelper.requestWithURL(url, paramsMap, httpResponseHandler);
	}

	// 注册
	public void register(User user, JsonHttpResponseHandler httpResponseHandler) {
		// 登录请求
		HttpRequestHelper requestHelper = HttpRequestHelper
				.DefaultHttpRequestHelper();
		// 请求地址
		String url = BaseHandle.requestUrlWithPath(APIconfig.API_REGISTER);
		// 请求参数
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put(User.KEY_NAME, user.getUsername());
		paramsMap.put(User.KEY_PASSWORD, SecurityUtil.md5(user.getPassword()));
		paramsMap.put(User.KEY_EMAIL, user.getEmail());
		paramsMap.put(User.KEY_NICKNAME, user.getNickname());

		requestHelper.requestWithURL(url, paramsMap, httpResponseHandler);
	}
}
