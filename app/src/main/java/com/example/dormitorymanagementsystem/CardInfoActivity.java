package com.example.dormitorymanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CardInfoActivity extends AppCompatActivity implements View.OnClickListener {
    //校园卡信息，相当于是余额+校园卡账单
    //TODO：我那啥，我得做分页来着，就固定一页五条吧
    //TODO：模糊搜索？？？

    Button back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);

        back=findViewById(R.id.backFromCardInfo);
        back.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromCardInfo:
                CardInfoActivity.this.finish();
                break;
        }
    }
}
