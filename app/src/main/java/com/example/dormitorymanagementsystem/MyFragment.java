package com.example.dormitorymanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    Button login_btn,personal_info,card_info,notification,suggestion,change_password;
    TextView user_name;
    String TAG="MyFragment";
    ImageView avatar;
    String studentId,passWord,student_name;
    ChangePasswordDialog changePasswordDialog;
    DefaultDialog defaultDialog;

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        final SharedPreferences loginInfo=this.getActivity().getSharedPreferences("loginInfo",0);
        studentId=loginInfo.getString("studentId","");
        passWord=loginInfo.getString("passWord","");

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
                public void onClick(View v) {    //跳转到登录界面
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            personal_info.setOnClickListener(default_listener);
            card_info.setOnClickListener(default_listener);
            notification.setOnClickListener(default_listener);
            suggestion.setOnClickListener(default_listener);
            change_password.setOnClickListener(default_listener);
        }

        else {//用户已经登录
            //退出按钮
            login_btn.setText("退出");
            //退出就是把loginInfo这个sharedpreference清空
            login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = loginInfo.edit();
                    editor.clear();
                    editor.apply();
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }
            });

            //用户名
            student_name=loginInfo.getString("student_name","");
            user_name.setText(student_name);

            //用户头像
            avatar.setImageResource(R.drawable.an_avatar);

            //个人信息
            personal_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),PersonalInfoActivity.class);
                    startActivity(intent);
                }
            });

            //校园卡信息or校园卡账单
            card_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),CardInfoActivity.class);
                    startActivity(intent);
                }
            });

            //通知信息
            notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),NoteActivity.class);
                    startActivity(intent);
                }
            });

            //建议与反馈
            suggestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),SuggestionActivity.class);
                    startActivity(intent);
                }
            });

            //修改密码
            change_password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //defaultDialog=new DefaultDialog(getActivity(),"默认");
                    changePasswordDialog=new ChangePasswordDialog(getActivity(),studentId,passWord);
                }
            });
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