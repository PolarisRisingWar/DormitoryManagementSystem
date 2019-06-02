package com.example.dormitorymanagementsystem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class LifePageAdapter extends FragmentPagerAdapter {

    private String[] title=new String[]{"天气","备忘录","新闻"};
    public LifePageAdapter(FragmentManager manager){
        super(manager);
    }
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new WeatherFragment();
        }else if (position==1){
            return new MemorandumFragment();
        }else {
            return new NewsFragment();
        }
    }

    public CharSequence getPageTitle(int position){
        return title[position];
    }

    @Override
    public int getCount() {//返回需要管理的fragment有多少个
        return 3;
    }
}
