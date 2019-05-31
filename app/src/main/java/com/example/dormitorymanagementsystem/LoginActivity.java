package com.example.dormitorymanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG="LoginActivity";
    EditText studentID,password;
    Button login;
    String studentId,passWord;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        studentID=findViewById(R.id.studentID);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {//这个布局里面只有一个按钮设了监听器
        studentId=studentID.getText().toString();
        passWord=password.getText().toString();
        Log.i(TAG, "student: "+studentId+"    "+passWord);
        //TODO：连接数据库/服务器
        //现在假设只有studentId=41711042,passWord=41711042时密码正确
        if(studentId.equals("41711042")&&passWord.equals(("41711042"))){
            Log.i(TAG, "student: 用户名与密码均正确");
        }
    }
}
