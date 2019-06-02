package com.example.dormitorymanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OutInActivity extends AppCompatActivity implements View.OnClickListener {

    Button backFromOutIn;//TODO：还是接服务器
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_in);

        backFromOutIn=findViewById(R.id.backFromOutIn);

        backFromOutIn.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId()==R.id.backFromOutIn){
            OutInActivity.this.finish();
        }
    }
}
