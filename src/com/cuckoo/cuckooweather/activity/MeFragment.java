package com.cuckoo.cuckooweather.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cuckoo.cuckooweather.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MeFragment extends Fragment {
	private ListView lv_menu = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_me, container, false);
		
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> menuMap1 = new HashMap<String, Object>();
		menuMap1.put("name", "订阅");
		HashMap<String, Object> menuMap2 = new HashMap<String, Object>();
		menuMap2.put("name", "提醒");
		HashMap<String, Object> menuMap3 = new HashMap<String, Object>();
		menuMap3.put("name", "设置");
		
		data.add(menuMap1);
		data.add(menuMap2);
		data.add(menuMap3);
		
		lv_menu = (ListView)view.findViewById(R.id.lv_menu);
		lv_menu.setAdapter(new SimpleAdapter(getActivity(), data, R.layout.me_item_layout, new String[]{"name"}, new int[]{R.id.tv_itemname}));

		return view;		
	}
}
