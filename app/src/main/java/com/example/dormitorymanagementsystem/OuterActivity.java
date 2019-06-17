package com.example.dormitorymanagementsystem;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Calendar;

public class OuterActivity extends AppCompatActivity implements View.OnClickListener,Runnable, RadioGroup.OnCheckedChangeListener {

    Button back,submit;
    EditText studentID,name,idnumber,time;
    String studentId,TAG="OuterActivity";
    DefaultDialog defaultDialog;
    SqlHelper sqlHelper;
    String sql;
    RadioGroup radioGroup;
    int out_or_in=1;//这个是这样，用户默认选中出寝室，也就是1

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outer);

        SharedPreferences loginInfo = OuterActivity.this.getSharedPreferences("loginInfo", 0);
        studentId = loginInfo.getString("studentId", "");

        studentID=findViewById(R.id.student_id_in_outer);
        name=findViewById(R.id.outer_name);
        idnumber=findViewById(R.id.outer_idnumber);
        time=findViewById(R.id.outer_time);
        back=findViewById(R.id.backFromOuter);
        submit=findViewById(R.id.submit_outer);
        radioGroup=findViewById(R.id.outer_radiogroup);

        studentID.setText(studentId);

        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        time.setText(year+"-"+month+"-"+day+" "+hour+":"+minute);

        back.setOnClickListener(this);
        submit.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromOuter:
                OuterActivity.this.finish();
                break;
            case R.id.submit_outer:
                Thread sql_insert=new Thread(this);
                sql_insert.start();
                defaultDialog=new DefaultDialog(OuterActivity.this,"您的外部人员出入登记表已提交成功！");
                break;
        }
    }

    public void run() {
        String thisname=name.getText().toString();
        String idno=idnumber.getText().toString();
        String nowtime=time.getText().toString();

        sql="insert into `outer_recor` values(null,'"+out_or_in+"','"+studentId+"','"+thisname+"','"+
                idno+"','"+nowtime+"');";
        sqlHelper=new SqlHelper();
        sqlHelper.onUpdate(sql);
        sqlHelper.onFinish();
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //检测用户选择的是出还是入
        switch (checkedId){
            case R.id.outer_in:    //入
                out_or_in=0;
                break;
            case R.id.outer_out:    //出
                out_or_in=1;
                break;
        }
    }
}
