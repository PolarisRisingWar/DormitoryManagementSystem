package com.example.dormitorymanagementsystem;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class DailyActivity extends AppCompatActivity implements View.OnClickListener {

    Button backFromDaily,repair,water_purchase,study_room,water_use;
    DefaultDialog defaultDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        backFromDaily=findViewById(R.id.backFromDaily);
        repair=findViewById(R.id.repair);
        water_purchase=findViewById(R.id.water_purchase);
        study_room=findViewById(R.id.study_room);
        water_use=findViewById(R.id.water_use);

        backFromDaily.setOnClickListener(this);
        repair.setOnClickListener(this);
        water_purchase.setOnClickListener(this);
        study_room.setOnClickListener(this);
        water_use.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId()==R.id.backFromDaily){
            DailyActivity.this.finish();
        }
        else if(v.getId()==R.id.repair){
            AlertDialog.Builder normalDialog = new AlertDialog.Builder(DailyActivity.this);
            //设置布局
            LayoutInflater inflater = LayoutInflater.from(DailyActivity.this);
            View view = inflater.inflate(R.layout.dialog_rapair_or_water_purchase, null);
            normalDialog.setView(view);
            normalDialog.setPositiveButton("开始付款",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            new PictureDialog(DailyActivity.this,R.drawable.money_ercode);
                        }
                    });
            normalDialog.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            normalDialog.show();
        }
        else if(v.getId()==R.id.water_purchase){
            AlertDialog.Builder normalDialog = new AlertDialog.Builder(DailyActivity.this);
            //设置布局
            LayoutInflater inflater = LayoutInflater.from(DailyActivity.this);
            View view = inflater.inflate(R.layout.dialog_rapair_or_water_purchase, null);
            normalDialog.setView(view);
            normalDialog.setPositiveButton("开始付款",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            new PictureDialog(DailyActivity.this,R.drawable.money_ercode);
                        }
                    });
            normalDialog.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            normalDialog.show();
        }
        else if(v.getId()==R.id.study_room){
            defaultDialog=new DefaultDialog(DailyActivity.this,this.getString(R.string.default_dialog));
        }
        else if(v.getId()==R.id.water_use){
            defaultDialog=new DefaultDialog(DailyActivity.this,"您住在榕园1楼，现在是夏季6月\n推荐您在17:00-17:36时间段中用水");
        }
    }
}
