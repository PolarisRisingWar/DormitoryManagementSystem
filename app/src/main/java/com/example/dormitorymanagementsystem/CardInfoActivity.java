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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CardInfoActivity extends AppCompatActivity implements View.OnClickListener,Runnable {
    //校园卡信息，相当于是余额+校园卡账单

    //这边的余额是真实的。因为数据库是主键自增存储（按时间顺序），所以最后那个余额就是真实余额
    //逻辑是按序输出到listview的item里。想要实现倒序输出，上拉翻页的效果，但是我一共就没几条数据，懒得搞这么多幺蛾子了

    Button back;
    ImageView search;
    DefaultDialog defaultDialog;
    String TAG="CardInfoActivity",studentId;
    String balance;
    SqlHelper sqlHelper;
    ListView listView;
    List<String> data;
    Handler handler;
    ArrayAdapter adapter;
    TextView balance_textview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);

        back=findViewById(R.id.backFromCardInfo);
        search=findViewById(R.id.search_icon);  //就是那个搜索的图标
        listView=findViewById(R.id.card_info_listview);
        balance_textview=findViewById(R.id.card_balance_info);

        back.setOnClickListener(this);
        search.setOnClickListener(this);

        //listview展示相关
        data=new ArrayList<String>();

        final SharedPreferences loginInfo=CardInfoActivity.this.getSharedPreferences("loginInfo",0);
        studentId=loginInfo.getString("studentId","");

        //到子线程中去查询数据库
        Thread sql_query=new Thread(this);
        sql_query.start();

        //从子线程返回到主线程
        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==5){
                    adapter=new ArrayAdapter<String>(CardInfoActivity.this,android.R.layout.simple_list_item_1,data);
                    listView.setAdapter(adapter);
                    balance_textview.setText(balance);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromCardInfo:
                CardInfoActivity.this.finish();
                break;
            case R.id.search_icon:
                defaultDialog=new DefaultDialog(CardInfoActivity.this,this.getString(R.string.default_dialog));
                //弹出默认弹窗
        }
    }

    public void run() {
        //查询数据库
        String sql = "select * from campus_card WHERE student_id='"+studentId+"'";
        sqlHelper=new SqlHelper();
        sqlHelper.onQuery(sql);
        try {
            while(sqlHelper.rs.next()){
                data.add("时间："+sqlHelper.rs.getString(3)+"    金额："+sqlHelper.rs.getString(4)
                +"    余额："+sqlHelper.rs.getString(5)+"    原因："+sqlHelper.rs.getString(6));
                balance=sqlHelper.rs.getString(5);
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
