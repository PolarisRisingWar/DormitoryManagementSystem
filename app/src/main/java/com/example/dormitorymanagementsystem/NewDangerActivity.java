package com.example.dormitorymanagementsystem;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewDangerActivity extends AppCompatActivity implements View.OnClickListener,Runnable {

    EditText studentID,name,date,remarks;
    Button back,submit;
    String nowdate,studentId,thisname,remark,insert_sql;
    DefaultDialog defaultDialog;
    SqlHelper sqlHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_danger);

        SharedPreferences loginInfo = NewDangerActivity.this.getSharedPreferences("loginInfo", 0);
        studentId = loginInfo.getString("studentId", "");

        studentID=findViewById(R.id.student_id_in_new_danger);
        name=findViewById(R.id.danger_name);
        date=findViewById(R.id.date_in_new_danger);
        remarks=findViewById(R.id.danger_remark);
        back=findViewById(R.id.backFromNewDanger);
        submit=findViewById(R.id.submit_danger);

        back.setOnClickListener(this);
        submit.setOnClickListener(this);

        studentID.setText(studentId);

        //获取当前日期
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        nowdate = sdf.format(today);
        date.setText(nowdate);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromNewDanger:
                NewDangerActivity.this.finish();
                break;
            case R.id.submit_danger:
                Thread sql_insert=new Thread(this);
                sql_insert.start();
                defaultDialog=new DefaultDialog(NewDangerActivity.this,"您的危险品登记表已提交成功！");
                break;
        }
    }

    public void run() {
        //将数据插入数据库中
        thisname=name.getText().toString();
        String thisdate=date.getText().toString();
        remark=remarks.getText().toString();

        insert_sql="insert into danger_record values(null,'0','"+studentId+"','"+thisname+"','"+thisdate+"','"+remark+"');";
        sqlHelper=new SqlHelper();
        sqlHelper.onUpdate(insert_sql);
        sqlHelper.onFinish();
    }
}
