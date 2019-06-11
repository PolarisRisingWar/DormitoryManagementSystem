package com.example.dormitorymanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class DormFragment extends Fragment {
    String TAG="DormFragment";

    public DormFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dorm, container, false);
    }

    Button out_in_management,danger_management,card_management,daily_management;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final SharedPreferences loginInfo=this.getActivity().getSharedPreferences("loginInfo",0);
        String studentId=loginInfo.getString("studentId","");
        String passWord=loginInfo.getString("passWord","");

        out_in_management=getActivity().findViewById(R.id.out_in_management);
        danger_management=getActivity().findViewById(R.id.danger_management);
        card_management=getActivity().findViewById(R.id.card_management);
        daily_management=getActivity().findViewById(R.id.daily_management);

        if(studentId==""&&passWord==""){
            out_in_management.setOnClickListener(default_listener);
            danger_management.setOnClickListener(default_listener);
            card_management.setOnClickListener(default_listener);
            daily_management.setOnClickListener(default_listener);
        }
        else {
            out_in_management.setOnClickListener(jump2_listener(new OutInActivity()));
            danger_management.setOnClickListener(jump2_listener(new DangerActivity()));
            card_management.setOnClickListener(jump2_listener(new CardActivity()));
            daily_management.setOnClickListener(jump2_listener(new DailyActivity()));
        }
    }

    View.OnClickListener jump2_listener(final Activity activity) {
        return new View.OnClickListener() {  //这个是登陆后，各个按钮跳转到不同activity
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity.getClass());
                startActivity(intent);
            }
        };
    }

    View.OnClickListener default_listener=new View.OnClickListener() {//这个是登录前许多按钮的默认监听器
        @Override
        public void onClick(View v) {
            Toast toast=Toast.makeText(getActivity(),"请您登录账号", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    };
}
