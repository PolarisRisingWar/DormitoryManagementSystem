package com.example.dormitorymanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,Runnable {

    String TAG="LoginActivity";
    EditText studentID,password;
    Button login,back;
    String studentId,passWord;
    SqlHelper sqlHelper;
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
    public void onClick(View v) {
        if(v.getId()==R.id.login){
            studentId=studentID.getText().toString();
            passWord=password.getText().toString();//获得用户输入的两条数据

            //我来连数据库了
            Thread sql_thread=new Thread(this);
            sql_thread.start();
        }
        else if(v.getId()==R.id.backFromLogin){
            LoginActivity.this.finish();
        }
    }

    public void run() {
        //这个子线程连接数据库，查询数据库中是否存在该用户
        String sql = "select * from student WHERE student_id='"+studentId+"' and secret_code='"+passWord+"'";
        sqlHelper=new SqlHelper();
        sqlHelper.onQuery(sql);
        try {
            if(sqlHelper.rs.next()){    //用户名与密码均正确
                SharedPreferences loginInfo=getSharedPreferences("loginInfo",0);
                SharedPreferences.Editor editor = loginInfo.edit();
                editor.putString("studentId",studentId);
                editor.putString("passWord",passWord);
                
                //这边考虑的是查都查了不如把所有信息都传过来啦，就不用隔壁fragment还要找子线程什么的啦
                /*for(int i=1;i<=9;i++){
                    Log.i(TAG, "此人的个人信息有"+sqlHelper.rs.getString(i));
                }*/
                //玄学啊！为什么不能用columnLabel啊！
                editor.putString("student_name",sqlHelper.rs.getString(2));//用户名
                editor.putString("in_year",sqlHelper.rs.getString(3).substring(0,4));//入学年份
                //这个是这样的，其实我在MySQL里存储的的确是year格式，但是不知道为什么变成字符串就是2017-1-1了
                //我就只截取了前四个字母即2017
                editor.putString("phone",sqlHelper.rs.getString(4));//电话号
                editor.putString("profession",sqlHelper.rs.getString(5));//专业
                editor.putString("institute",sqlHelper.rs.getString(6));//学院
                editor.putString("dorm_building",sqlHelper.rs.getString(7));//寝室楼
                editor.putString("dorm_number",sqlHelper.rs.getString(8));//寝室号

                editor.apply();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(LoginActivity.this,"请您输入正确的学号和密码", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Log.i(TAG, "run: 在这里报错了");
        }
        sqlHelper.onFinish();
    }
}
