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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener,Runnable {
    //通知信息界面

    Button back;
    ListView listView;
    List<String> data;
    Handler handler;
    ArrayAdapter adapter;
    String studentId;
    SqlHelper sqlHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        back=findViewById(R.id.backFromNote);
        listView=findViewById(R.id.note_listview);

        back.setOnClickListener(this);

        data=new ArrayList<String>();

        final SharedPreferences loginInfo=NoteActivity.this.getSharedPreferences("loginInfo",0);
        studentId=loginInfo.getString("studentId","");

        Thread sql_query=new Thread(this);
        sql_query.start();

        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==5){
                    adapter=new ArrayAdapter<String>(NoteActivity.this,android.R.layout.simple_list_item_1,data);
                    listView.setAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromNote:
                NoteActivity.this.finish();
                break;
        }
    }

    public void run() {
        //查询数据库
        String sql = "select * from notification WHERE student_id='"+studentId+"'";
        sqlHelper=new SqlHelper();
        sqlHelper.onQuery(sql);
        try {
            while(sqlHelper.rs.next()){
                data.add("通知时间："+sqlHelper.rs.getString(3)+"    发送人："+sqlHelper.rs.getString(4)
                        +"    消息内容："+sqlHelper.rs.getString(5));
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
