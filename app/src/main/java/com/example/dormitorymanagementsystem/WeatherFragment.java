package com.example.dormitorymanagementsystem;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.heweather.plugin.view.HeContent;
import com.heweather.plugin.view.HeWeatherConfig;
import com.heweather.plugin.view.HorizonView;
import com.heweather.plugin.view.LeftLargeView;
import com.heweather.plugin.view.VerticalView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {


    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        HeWeatherConfig.init("faf062fad2f34f31a151301c88ec6f0f","成都");

        //左侧大布局右侧双布局控件
        LeftLargeView llView = getActivity().findViewById(R.id.ll_view);

//获取左侧大布局
        LinearLayout leftLayout = llView.getLeftLayout();
//获取右上布局
        LinearLayout rightTopLayout = llView.getRightTopLayout();
//获取右下布局
        LinearLayout rightBottomLayout = llView.getRightBottomLayout();

//设置布局的背景圆角角度（单位：dp），颜色，边框宽度（单位：px），边框颜色
        llView.setStroke(5, Color.parseColor("#313a44"), 1, Color.BLACK);

//添加温度描述到左侧大布局
//第一个参数为需要加入的布局
//第二个参数为文字大小，单位：sp
//第三个参数为文字颜色，默认白色
        llView.addTemp(leftLayout, 40, Color.WHITE);
//添加温度图标到右上布局，第二个参数为图标宽高（宽高1：1，单位：dp）
        llView.addWeatherIcon(rightTopLayout, 20);
//添加预警图标到右上布局
        llView.addAlarmIcon(rightTopLayout, 20);
//添加预警描述到右上布局
        llView.addAlarmTxt(rightTopLayout, 20);
//添加文字AQI到右上布局
        llView.addAqiText(rightTopLayout, 20);
//添加空气质量到右上布局
        llView.addAqiQlty(rightTopLayout, 20);
//添加空气质量数值到右上布局
        llView.addAqiNum(rightTopLayout, 20);
//添加地址信息到右上布局
        llView.addLocation(rightTopLayout, 20, Color.WHITE);
//添加天气描述到右下布局
        llView.addCond(rightBottomLayout, 20, Color.WHITE);
//添加风向图标到右下布局
        llView.addWindIcon(rightBottomLayout, 20);
//添加风力描述到右下布局
        llView.addWind(rightBottomLayout, 20, Color.WHITE);
//添加降雨图标到右下布局
        llView.addRainIcon(rightBottomLayout, 20);
//添加降雨描述到右下布局
        llView.addRainDetail(rightBottomLayout, 20, Color.WHITE);
//设置控件的对齐方式，默认居中
        llView.setViewGravity(HeContent.GRAVITY_LEFT);
//显示布局
        llView.show();
    }
}
