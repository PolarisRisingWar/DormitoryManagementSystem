package com.example.dormitorymanagementsystem;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {

    Button backFromCard,lost_found,report_loss,reissue,recharge,transfer,transaction_record;
    DefaultDialog defaultDialog;
    PictureDialog pictureDialog;
    EasyPNDialog easyPNDialog;
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
                easyPNDialog=new EasyPNDialog(CardActivity.this,"请问您确定要将校园卡进行挂失吗？",0);
                break;
            case R.id.reissue:
                easyPNDialog=new EasyPNDialog(CardActivity.this,"请您预约前往其孜楼拿卡的时间",1);
                break;
            case R.id.recharge:
                pictureDialog=new PictureDialog(CardActivity.this,R.drawable.money_ercode);
                break;
            case R.id.transfer:
                //我懒得再写一个dialog类了，直接上吧
                //难道我真的应该写接口吗？我宁可复制粘贴好吗
                //创建dialog构造器
                AlertDialog.Builder normalDialog = new AlertDialog.Builder(CardActivity.this);
                //设置布局
                LayoutInflater inflater = LayoutInflater.from(CardActivity.this);
                View view = inflater.inflate(R.layout.dialog_transfer, null);
                normalDialog.setView(view);
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                new DefaultDialog(CardActivity.this,"恭喜您转账成功！\n转账后续的处理功能正在开发中，敬请期待！");
                                }
                        });
                normalDialog.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                normalDialog.show();
                break;
        }
    }

    void jump(Activity activity){
        //跳转到其他activity
        Intent intent = new Intent(CardActivity.this, activity.getClass());
        startActivity(intent);
    }

}
