package com.example.dormitorymanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DangerActivity extends AppCompatActivity implements View.OnClickListener {

    Button backFromDanger;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger);

        backFromDanger=findViewById(R.id.backFromDanger);

        backFromDanger.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId()==R.id.backFromDanger){
            DangerActivity.this.finish();
        }
    }
}
