package com.example.dormitorymanagementsystem;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DailyOutInActivity extends AppCompatActivity implements View.OnClickListener,Runnable {

    Button back;
    ListView listView;
    List<String> data;
    Handler handler;
    ArrayAdapter adapter;
    String studentId;
    SqlHelper sqlHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_out_in);

        back=findViewById(R.id.backFromDailyOutIn);
        listView=findViewById(R.id.daily_out_in_listview);

        back.setOnClickListener(this);

        data=new ArrayList<String>();

        final SharedPreferences loginInfo=DailyOutInActivity.this.getSharedPreferences("loginInfo",0);
        studentId=loginInfo.getString("studentId","");

        Thread sql_query=new Thread(this);
        sql_query.start();

        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==5){
                    adapter=new ArrayAdapter<String>(DailyOutInActivity.this,android.R.layout.simple_list_item_1,data);
                    listView.setAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromDailyOutIn:
                DailyOutInActivity.this.finish();
                break;
        }
    }

    public void run() {
        //查询数据库
        String sql = "select * from daily_out_in WHERE student_id='"+studentId+"'";
        sqlHelper=new SqlHelper();
        sqlHelper.onQuery(sql);
        try {
            while(sqlHelper.rs.next()){
                //判断是出还是入
                String out_or_in;
                if(sqlHelper.rs.getString(4).equals("1")){
                    out_or_in="出";
                }
                else{
                    out_or_in="进";
                }

                data.add(out_or_in+"门日期："+sqlHelper.rs.getString(3));
            }
            sqlHelper.onFinish();
        }
        catch (Exception e){

        }
        Message msg = handler.obtainMessage();
        msg.what = 5;
        handler.sendMessage(msg);
    }
}
