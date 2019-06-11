package com.example.dormitorymanagementsystem;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SuggestionActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,Runnable {
    //建议界面。业务逻辑是将用户输入的建议内容插入到表suggestion中

    Button back,submit;
    RadioGroup radioGroup;
    String studentId,sender,nowDate,content;    //一开始先初始化一下学生的信息（从sharedpreference中获取）
    //sender默认设为studentId，如果学生选择了匿名按钮，就设为“匿名”
    SqlHelper sqlHelper;
    EditText contents;
    DefaultDialog defaultDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        back=findViewById(R.id.backFromSuggestion);
        radioGroup=findViewById(R.id.suggestion_radiogroup);
        submit=findViewById(R.id.suggestion_submit);
        contents=findViewById(R.id.suggestion_content);

        back.setOnClickListener(this);
        submit.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);

        final SharedPreferences loginInfo=SuggestionActivity.this.getSharedPreferences("loginInfo",0);
        studentId=loginInfo.getString("studentId","");

        sender=studentId;
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromSuggestion:
                SuggestionActivity.this.finish();
                break;
            case R.id.suggestion_submit:    //点击提交按钮
                Thread sql_insert=new Thread(this);
                sql_insert.start();
                defaultDialog=new DefaultDialog(SuggestionActivity.this,"您的建议已发送成功！");
                break;
        }
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {    //这个是radiogroup的监听器
        switch (checkedId){
            case R.id.suggestion_realname:    //实名按钮（为默认选中项）
                sender=studentId;
                break;
            case R.id.suggestion_anonymous:    //匿名按钮
                sender="匿名";
                break;
        }
    }

    public void run() {
        //老规矩，在子线程里面连数据库

        //获取当前时间
        Date today= Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        nowDate=sdf.format(today);

        //获取主线程中用户输入的内容
        content=contents.getText().toString();

        String sql = "insert into suggestion values(NULL,'"+sender+"','"+nowDate+"','"+content+"');";
        sqlHelper=new SqlHelper();
        sqlHelper.onUpdate(sql);
        sqlHelper.onFinish();
    }
}
