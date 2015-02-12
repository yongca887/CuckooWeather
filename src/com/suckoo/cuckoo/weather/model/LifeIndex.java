package com.suckoo.cuckoo.weather.model;

import org.json.JSONException;
import org.json.JSONObject;

public class LifeIndex {
	private String zs;
	private String des;
	private String tipt;
	private String title;
	
	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getTipt() {
		return tipt;
	}

	public void setTipt(String tipt) {
		this.tipt = tipt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static LifeIndex parseJson(JSONObject json) {
		LifeIndex index = new LifeIndex();
		
		try {
			index.zs = json.get("zs").toString();
			index.des = json.get("des").toString();
			index.tipt = json.get("tipt").toString();
			index.title = json.get("title").toString();		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return index;
	}
}
