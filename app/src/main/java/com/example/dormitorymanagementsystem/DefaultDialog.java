package com.example.dormitorymanagementsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DefaultDialog {
    DefaultDialog(Context context,String s){
        //创建dialog构造器
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(context);
        //设置内容
        normalDialog.setMessage(s);
        //设置按钮
        normalDialog.setPositiveButton("确定"
                , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        //创建并显示
        normalDialog.create().show();
    }
}
