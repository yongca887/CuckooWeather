package com.cuckoo.cuckooweather.activity;

import com.cuckoo.cuckooweather.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {
	private RadioGroup radioGroup = null;
    private FragmentManager fragmentManager = null;
    private WeatherFragment weatherFragment = null;
    private LifeIndexFragment lifeFragment = null;
    private MeFragment meFragment = null;
    private Fragment mContent = null;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main_layout);
		
		fragmentManager = getFragmentManager();
		lifeFragment = new LifeIndexFragment();
		meFragment = new MeFragment();
		
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		weatherFragment = new WeatherFragment();
		transaction.add(R.id.ui_content, weatherFragment);
		mContent = weatherFragment;
		transaction.commit();
		
		radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
		radioGroup.check(R.id.bottom_tabbar_rb_1);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                //判断选中的radiobutton,触发不同的事件
                if (checkedRadioButtonId == R.id.bottom_tabbar_rb_1) {
                	
                	switchContent(mContent, weatherFragment);
                } else if (checkedRadioButtonId == R.id.bottom_tabbar_rb_2) {
                	
                	switchContent(mContent, lifeFragment);
                } else if(checkedRadioButtonId == R.id.bottom_tabbar_rb_3) {
                	                  
                	switchContent(mContent, meFragment);
                }
			}
		});
	}
	
	public void switchContent(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.ui_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
