package com.example.dormitorymanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DailyActivity extends AppCompatActivity implements View.OnClickListener {

    Button backFromDaily;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        backFromDaily=findViewById(R.id.backFromDaily);

        backFromDaily.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId()==R.id.backFromDaily){
            DailyActivity.this.finish();
        }
    }
}
