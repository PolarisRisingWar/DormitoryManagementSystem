package com.example.dormitorymanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            defaultDialog=new DefaultDialog(DailyActivity.this,this.getString(R.string.default_dialog));
        }
        else if(v.getId()==R.id.water_purchase){
            defaultDialog=new DefaultDialog(DailyActivity.this,this.getString(R.string.default_dialog));
        }
        else if(v.getId()==R.id.study_room){
            defaultDialog=new DefaultDialog(DailyActivity.this,this.getString(R.string.default_dialog));
        }
        else if(v.getId()==R.id.water_use){
            defaultDialog=new DefaultDialog(DailyActivity.this,this.getString(R.string.default_dialog));
        }
    }
}
