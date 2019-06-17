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

public class OldDangerActivity extends AppCompatActivity implements View.OnClickListener,Runnable {

    Button back;
    ListView listView;
    List<String> data;
    Handler handler;
    ArrayAdapter adapter;
    String studentId;
    SqlHelper sqlHelper;
    String type;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_danger);

        back=findViewById(R.id.backFromOldDanger);
        listView=findViewById(R.id.danger_listview);

        back.setOnClickListener(this);

        data=new ArrayList<String>();

        final SharedPreferences loginInfo=OldDangerActivity.this.getSharedPreferences("loginInfo",0);
        studentId=loginInfo.getString("studentId","");

        Thread sql_query=new Thread(this);
        sql_query.start();

        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==5){
                    adapter=new ArrayAdapter<String>(OldDangerActivity.this,android.R.layout.simple_list_item_1,data);
                    listView.setAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromOldDanger:
                OldDangerActivity.this.finish();
                break;
        }
    }

    public void run() {
        //查询数据库
        String sql = "select * from danger_record WHERE student_id='"+studentId+"'";
        sqlHelper=new SqlHelper();
        sqlHelper.onQuery(sql);
        try {
            while(sqlHelper.rs.next()){
                //判断是审核通过、审核不通过还是待审核中
                if(sqlHelper.rs.getString(2).equals("2")){
                    type="审核不通过";
                }
                else if(sqlHelper.rs.getString(2).equals("1")){
                    type="审核通过";
                }
                else {
                    type="待审核";
                }

                data.add("物品名："+sqlHelper.rs.getString(4)+"\n时间："+sqlHelper.rs.getString(5)+
                        "\n备注："+sqlHelper.rs.getString(6)+"\n审核状态："+type);
            }
            sqlHelper.onFinish();
        }
        catch (Exception e){}
        Message msg = handler.obtainMessage();
        msg.what = 5;
        handler.sendMessage(msg);
    }
}
