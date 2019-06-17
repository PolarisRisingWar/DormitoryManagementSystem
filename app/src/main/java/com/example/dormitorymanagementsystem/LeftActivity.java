package com.example.dormitorymanagementsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LeftActivity extends AppCompatActivity implements View.OnClickListener,Runnable {
    //离校登记表
    //默认填写内容：学号（不可变更）、姓名（不可变更）、填写时间（不可变更）、联系电话、寝室楼和寝室号（不可变更）
    //需要手动填写的内容：目的地、离校时间、返校时间、陪同人员
    //此处填写的内容中，可变更的内容+学号需要全部插入数据库（数据库设计理论不是有个什么冗余不冗余的说法嘛）

    EditText studentID, name, time, phone, dorm, destination, leave_time, back_time, accompany;
    Button back, submit;
    String nowDate, studentId, student_name, telephone, dorm_building, dorm_number,insert_sql;
    DefaultDialog defaultDialog;
    SqlHelper sqlHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left);

        SharedPreferences loginInfo = LeftActivity.this.getSharedPreferences("loginInfo", 0);
        studentId = loginInfo.getString("studentId", "");
        student_name = loginInfo.getString("student_name", "");
        telephone = loginInfo.getString("phone", "");
        dorm_building = loginInfo.getString("dorm_building", "");
        dorm_number = loginInfo.getString("dorm_number", "");

        back = findViewById(R.id.backFromLeft);
        submit = findViewById(R.id.left_submit);

        studentID = findViewById(R.id.student_id_in_left);
        name = findViewById(R.id.student_name_in_left);
        time = findViewById(R.id.time_in_left);
        phone = findViewById(R.id.phone_in_left);
        dorm = findViewById(R.id.dorm_in_left);
        destination = findViewById(R.id.destination_in_left);
        leave_time = findViewById(R.id.left_time);
        back_time = findViewById(R.id.back_time);
        accompany = findViewById(R.id.company);

        back.setOnClickListener(this);
        submit.setOnClickListener(this);
        leave_time.setOnClickListener(this);
        back_time.setOnClickListener(this);

        //现在开始花式初始化数据
        //从sharedpreference里获得数据
        studentID.setText(studentId);
        name.setText(student_name);
        phone.setText(telephone);
        dorm.setText(dorm_building + dorm_number);

        //获取当前日期
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        nowDate = sdf.format(today);
        time.setText(nowDate);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backFromLeft:
                LeftActivity.this.finish();
                break;
            case R.id.left_time:
                showDatePickerDialog(leave_time);
                break;
            case R.id.back_time:
                showDatePickerDialog(back_time);
                break;
            case R.id.left_submit:
                Thread sql_insert=new Thread(this);
                sql_insert.start();
                defaultDialog=new DefaultDialog(LeftActivity.this,"您的离校登记表已提交成功！");
                break;
        }
    }

    //用户点击离校和返校日期时弹出选择日期的窗口
    private void showDatePickerDialog(final EditText editText) {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(LeftActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void run() {
        //将数据插入数据库中
        String dest=destination.getText().toString();
        String lefttime=leave_time.getText().toString();
        String backtime=back_time.getText().toString();
        String company=accompany.getText().toString();
        insert_sql="insert into `left_record` values(null,'"+studentId+
                "','"+nowDate+"','"+telephone+"','"+dest+"','"+lefttime+"','"+backtime+"','"+company+"');";
        sqlHelper=new SqlHelper();
        sqlHelper.onUpdate(insert_sql);
        sqlHelper.onFinish();
    }
}
