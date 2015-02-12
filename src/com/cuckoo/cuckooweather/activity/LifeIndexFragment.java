package com.cuckoo.cuckooweather.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cuckoo.cuckooweather.R;
import com.cuckoo.cuckooweather.util.Constant;
import com.suckoo.cuckoo.weather.model.LifeIndex;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class LifeIndexFragment extends Fragment {
	private GridView gv_index = null;
	private ArrayList<Integer> imageIds = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_lifeindex, container, false);
		
		//获取生活指南
		//首选项保存
		SharedPreferences mySPreferences
				 = getActivity().getSharedPreferences(Constant.MY_PREFS, Activity.MODE_PRIVATE);
		
		ArrayList<LifeIndex> indexs = new ArrayList<LifeIndex>();
        
        for(int i = 0;i < 6; i++) {
        	
        	String indexsString = mySPreferences.getString(Constant.PR_KEY_INDEX+i, "");
        	JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(indexsString);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        	LifeIndex index = LifeIndex.parseJson(jsonObject);
        	
        	indexs.add(index);
        }
        
        imageIds = new ArrayList<Integer>();
        imageIds.add(R.drawable.info_0);
        imageIds.add(R.drawable.info_1);
        imageIds.add(R.drawable.info_2);
        imageIds.add(R.drawable.info_3);
        imageIds.add(R.drawable.info_4);
        imageIds.add(R.drawable.info_5);
		
		gv_index = (GridView)view.findViewById(R.id.gv_index);
		gv_index.setAdapter(new MyGridViewAdapter(getActivity(), indexs, imageIds));

		return view;
	}
	
	public class MyGridViewAdapter extends BaseAdapter {
		private ArrayList<LifeIndex> indexs = null;
		private Context context = null;
		private ArrayList<Integer> imgs = null;
		
		public MyGridViewAdapter(Context _context, ArrayList<LifeIndex> _indexs, ArrayList<Integer> _imgs) {
			indexs = _indexs;
			context = _context;
			imgs = _imgs;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return indexs.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return indexs.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View itemView = inflater.inflate(R.layout.lifeindex_item_layout, null);
				
				TextView tv_indexname = (TextView)itemView.findViewById(R.id.tv_indexname);
				TextView tv_index = (TextView)itemView.findViewById(R.id.tv_index);
				
				LifeIndex index = indexs.get(position);
				tv_index.setText(index.getTitle());
				tv_indexname.setText(index.getZs());
				
				ImageView img = (ImageView)itemView.findViewById(R.id.img_index);
				img.setImageResource(imgs.get(position));
				
				return itemView;
			} else {
				TextView tv_indexname = (TextView)convertView.findViewById(R.id.tv_indexname);
				TextView tv_index = (TextView)convertView.findViewById(R.id.tv_index);
				
				LifeIndex index = indexs.get(position);
				tv_index.setText(index.getTitle());				
				tv_indexname.setText(index.getZs());
				
				ImageView img = (ImageView)convertView.findViewById(R.id.img_index);
				img.setImageResource(imgs.get(position));
			}
			
			return convertView;
		}
		
	}
}


