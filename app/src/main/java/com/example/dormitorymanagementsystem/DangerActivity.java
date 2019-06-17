package com.example.dormitorymanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DangerActivity extends AppCompatActivity implements View.OnClickListener {

    Button backFromDanger,old_record,new_record;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger);

        backFromDanger=findViewById(R.id.backFromDanger);
        old_record=findViewById(R.id.danger_record);
        new_record=findViewById(R.id.new_danger);

        backFromDanger.setOnClickListener(this);
        old_record.setOnClickListener(this);
        new_record.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromDanger:
                DangerActivity.this.finish();
                break;
            case R.id.danger_record:
                jump(new OldDangerActivity());
                break;
            case R.id.new_danger:
                jump(new NewDangerActivity());
                break;
        }
    }

    void jump(Activity activity){
        //跳转到其他activity
        Intent intent = new Intent(DangerActivity.this, activity.getClass());
        startActivity(intent);
    }
}
