package com.example.dormitorymanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {

    Button backFromCard,lost_found,report_loss,reissue,recharge,transfer,transaction_record;
    DefaultDialog defaultDialog;
    PictureDialog pictureDialog;
    String TAG="CardActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        backFromCard=findViewById(R.id.backFromCard);
        lost_found=findViewById(R.id.lost_found);
        report_loss=findViewById(R.id.report_loss);
        reissue=findViewById(R.id.reissue);
        recharge=findViewById(R.id.recharge);
        transfer=findViewById(R.id.transfer);
        transaction_record=findViewById(R.id.transaction_record);

        backFromCard.setOnClickListener(this);
        lost_found.setOnClickListener(this);
        report_loss.setOnClickListener(this);
        reissue.setOnClickListener(this);
        recharge.setOnClickListener(this);
        transfer.setOnClickListener(this);
        transaction_record.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFromCard:
                CardActivity.this.finish();
                break;
            case R.id.lost_found:
                jump(new LostFoundActivity());
                break;
            case R.id.transaction_record:
                jump(new CardInfoActivity());
                break;
            case R.id.report_loss:
                defaultDialog=new DefaultDialog(CardActivity.this,this.getString(R.string.default_dialog));
                break;
            case R.id.reissue:
                defaultDialog=new DefaultDialog(CardActivity.this,this.getString(R.string.default_dialog));
                break;
            case R.id.recharge:
                pictureDialog=new PictureDialog(CardActivity.this,R.drawable.money_ercode);
                break;
            case R.id.transfer:
                defaultDialog=new DefaultDialog(CardActivity.this,this.getString(R.string.default_dialog));
                break;
        }
    }

    void jump(Activity activity){
        //跳转到其他activity
        Intent intent = new Intent(CardActivity.this, activity.getClass());
        startActivity(intent);
    }

}
