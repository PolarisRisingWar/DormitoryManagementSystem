package com.example.dormitorymanagementsystem;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Calendar;

public class BigActivity extends AppCompatActivity implements View.OnClickListener,Runnable, RadioGroup.OnCheckedChangeListener {

    Button back,submit;
    EditText studentID,name,time,remark;
    String studentId,TAG="OuterActivity";
    DefaultDialog defaultDialog;
    SqlHelper sqlHelper;
    String sql;
    RadioGroup radioGroup;
    int out_or_in=1;//这个是这样，用户默认选中出寝室，也就是1

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big);

        SharedPreferences loginInfo = BigActivity.this.getSharedPreferences("loginInfo", 0);
        studentId = loginInfo.getString("studentId", "");

        back=findViewById(R.id.backFromBig);
        submit=findViewById(R.id.submit_big);
        studentID=findViewById(R.id.student_id_in_big);
        name=findViewById(R.id.big_name);
        remark=findViewById(R.id.remarks_in_big);
        time=findViewById(R.id.big_time);
        radioGroup=findViewById(R.id.big_radiogroup);

        back.setOnClickListener(this);
        submit.setOnClickListener(this);

        studentID.setText(studentId);

        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        time.setText(year+"-"+month+"-"+day+" "+hour+":"+minute);

        radioGroup.setOnCheckedChangeListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromBig:
                BigActivity.this.finish();
                break;
            case R.id.submit_big:
                Thread sql_insert=new Thread(this);
                sql_insert.start();
                defaultDialog=new DefaultDialog(BigActivity.this,"您的外部人员出入登记表已提交成功！");
                break;
        }
    }

    public void run() {
        String thisname=name.getText().toString();
        String thistime=time.getText().toString();
        String remarks=remark.getText().toString();

        sql="insert into big_record values(null,"+out_or_in+",'"+studentId+"','"+thisname+"','"+thistime+"','"+remarks+"');";
        sqlHelper=new SqlHelper();
        sqlHelper.onUpdate(sql);
        sqlHelper.onFinish();
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //检测用户选择的是出还是入
        switch (checkedId){
            case R.id.big_in:    //入
                out_or_in=0;
                break;
            case R.id.big_out:    //出
                out_or_in=1;
                break;
        }
    }
}
