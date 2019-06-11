package com.example.dormitorymanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OutInActivity extends AppCompatActivity implements View.OnClickListener {

    Button backFromOutIn,daily,left,outer,big;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_in);

        backFromOutIn=findViewById(R.id.backFromOutIn);
        daily=findViewById(R.id.daily_out_in);
        left=findViewById(R.id.out_record);
        outer=findViewById(R.id.outer_record);
        big=findViewById(R.id.big_out_in);

        backFromOutIn.setOnClickListener(this);
        daily.setOnClickListener(this);
        left.setOnClickListener(this);
        outer.setOnClickListener(this);
        big.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromOutIn:
                OutInActivity.this.finish();
                break;
            case R.id.daily_out_in:
                jump(new DailyOutInActivity());
                break;
            case R.id.out_record:
                //离校登记
        }
    }

    void jump(Activity activity){
        Intent intent = new Intent(OutInActivity.this, activity.getClass());
        startActivity(intent);
    }
}
