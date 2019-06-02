package com.example.dormitorymanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {

    Button backFromCard;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        backFromCard=findViewById(R.id.backFromCard);

        backFromCard.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId()==R.id.backFromCard){
            CardActivity.this.finish();
        }
    }
}
