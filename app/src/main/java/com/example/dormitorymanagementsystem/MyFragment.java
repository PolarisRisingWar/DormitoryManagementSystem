package com.example.dormitorymanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyFragment extends Fragment {
    public MyFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    //这是我在网上找到的一种实例化fragment布局文件里组件的方法
    Button login_btn,personal_info,card_info,notification,suggestion,change_password;
    TextView user_name;
    String TAG="MyFragment";
    ImageView avatar;
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        final SharedPreferences loginInfo=this.getActivity().getSharedPreferences("loginInfo",0);
        String studentId=loginInfo.getString("studentId","");
        String passWord=loginInfo.getString("passWord","");
        Log.i(TAG, "onActivityCreated: "+studentId+"    "+passWord);
        //如果有的话，studentId和passWord就分别是其值；如果没有的话，就都是空值；截止6.2，尚未报错

        login_btn=getActivity().findViewById(R.id.login_btn);
        personal_info=getActivity().findViewById(R.id.personal_info);
        card_info=getActivity().findViewById(R.id.card_info);
        notification=getActivity().findViewById(R.id.notification);
        suggestion=getActivity().findViewById(R.id.suggestion);
        change_password=getActivity().findViewById(R.id.change_password);
        user_name=getActivity().findViewById(R.id.user_name);
        avatar=getActivity().findViewById(R.id.avatar);

        if (studentId==""&&passWord=="") {//用户还没有登录
            login_btn.setText("登录");
            user_name.setText("用户名");
            avatar.setImageResource(R.drawable.default_avatar);
            login_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                /*MainActivity  mainActivity = (MainActivity) getActivity();
                mainActivity.go2LoginFragment();*/
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            //点击剩下所有控件都会弹出“请登录”Toast
            //为了少写几句代码，我写个综合性的View.OnClickListener default_listener放在最下面了
            personal_info.setOnClickListener(default_listener);
            card_info.setOnClickListener(default_listener);
            notification.setOnClickListener(default_listener);
            suggestion.setOnClickListener(default_listener);
            change_password.setOnClickListener(default_listener);
        }
        else {//用户已经登录
            login_btn.setText("退出");
            //退出就是把loginInfo这个sharedpreference清空
            login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = loginInfo.edit();
                    editor.clear();
                    editor.apply();
                    //底下这两句没用。跳转到哪个fragment都不行。必须要重来一遍本fragment创建过程才行
                    //MainActivity  mainActivity = (MainActivity) getActivity();
                    //mainActivity.go2DormFragment();
                    //光重启本fragment不行，毕竟隔壁fragment也要用这个sharedpreference
                    // 所以要刷新整个activity
                    // onActivityCreated(null);

                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }
            });
            //TODO：正经来说，这里面的图片、用户名应该援引自数据库的
            //我就先写个示意性的界面
            user_name.setText("信院老王");
            avatar.setImageResource(R.drawable.an_avatar);
            //TODO：一堆跳转过去的activity
        }
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