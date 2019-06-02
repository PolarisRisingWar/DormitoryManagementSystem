package com.example.dormitorymanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG="LoginActivity";
    EditText studentID,password;
    Button login,back;
    String studentId,passWord;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        studentID=findViewById(R.id.studentID);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        back=findViewById(R.id.backFromLogin);

        login.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {//这个布局里面只有一个按钮设了监听器
        if(v.getId()==R.id.login){
            studentId=studentID.getText().toString();
            passWord=password.getText().toString();//获得用户输入的两条数据
            //TODO：连接数据库/服务器
            //现在假设只有studentId=41711042,passWord=41711042时密码正确
            if(studentId.equals("41711042")&&passWord.equals(("41711042"))){//用户输入了正确的学号和密码
                Log.i(TAG, "student: 学号与密码均正确");
                //6.2加入
                //把学号和密码放入sharedpreference loginInfo中
                SharedPreferences loginInfo=getSharedPreferences("loginInfo",0);
                SharedPreferences.Editor editor = loginInfo.edit();
                editor.putString("studentId",studentId);
                editor.putString("passWord",passWord);
                editor.apply();
                //跳转回MainActivity
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(LoginActivity.this,"请您输入正确的学号和密码", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId()==R.id.backFromLogin){
            LoginActivity.this.finish();
        }
    }
}
