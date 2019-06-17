package com.example.dormitorymanagementsystem;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LostFoundActivity extends AppCompatActivity implements View.OnClickListener,Runnable {

    Button back,new_info,my_info;
    ImageView search;
    DefaultDialog defaultDialog;
    String studentId,TAG="LostFoundActivity";
    SqlHelper sqlHelper;
    ListView listView;
    List<String> data;
    Handler handler;
    ArrayAdapter adapter;
    String type="丢失信息";//1是失物，0是招领
    String sender="管理员";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found);

        back=findViewById(R.id.backFromLostFound);
        new_info=findViewById(R.id.new_lost_found);
        my_info=findViewById(R.id.my_lost_found);
        search=findViewById(R.id.lost_found_search_icon);
        listView=findViewById(R.id.lost_found_listview);

        back.setOnClickListener(this);
        search.setOnClickListener(this);
        new_info.setOnClickListener(this);
        my_info.setOnClickListener(this);

        data=new ArrayList<String>();

        final SharedPreferences loginInfo=LostFoundActivity.this.getSharedPreferences("loginInfo",0);
        studentId=loginInfo.getString("studentId","");

        //到子线程中去查询数据库
        Thread sql_query=new Thread(this);
        sql_query.start();

        //从子线程返回到主线程
        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==5){
                    adapter=new ArrayAdapter<String>(LostFoundActivity.this,android.R.layout.simple_list_item_1,data);
                    listView.setAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromLostFound:
                LostFoundActivity.this.finish();
                break;
            case R.id.lost_found_search_icon:
                defaultDialog=new DefaultDialog(LostFoundActivity.this,this.getString(R.string.default_dialog));
                break;
            case R.id.new_lost_found:
                defaultDialog=new DefaultDialog(LostFoundActivity.this,this.getString(R.string.default_dialog));
                break;
            case R.id.my_lost_found:
                defaultDialog=new DefaultDialog(LostFoundActivity.this,this.getString(R.string.default_dialog));
                break;
        }
    }

    public void run() {
        //查询数据库
        String sql = "select * from lost_and_found";
        sqlHelper=new SqlHelper();
        sqlHelper.onQuery(sql);
        try {
            while(sqlHelper.rs.next()){
                //判断是失物还是招领
                if(sqlHelper.rs.getString(2).equals("1")){
                    type="丢失信息";
                }
                else {
                    type="招领信息";
                }
                Log.i(TAG, "type为："+type);
                Log.i(TAG, "sender的原始数据为："+sqlHelper.rs.getString(3));

                //判断信息由学生个人发出还是由管理员发布
                if(sqlHelper.rs.getString(3)==null){
                    sender="管理员";
                }
                else {
                    sender="某个学生";
                }
                Log.i(TAG, "sender为"+sender);

                data.add(type+"\n发布人员："+sender+"\n发布人员联系方式："+sqlHelper.rs.getString(4)+
                        "\n物品名称："+sqlHelper.rs.getString(5)+
                        "\n（校园卡）学号："+sqlHelper.rs.getString(6)+
                        "\n发布时间："+sqlHelper.rs.getString(7)+
                        "\n备注："+sqlHelper.rs.getString(8));
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
