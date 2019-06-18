package com.example.dormitorymanagementsystem;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.DatePicker;

import java.util.Calendar;

public class EasyPNDialog {
    DefaultDialog defaultDialog;
    //一个简单的有确定和取消两个按键的类（根据输入的参数来置入功能）
    EasyPNDialog(final Context context,String string,final int i){
        //创建dialog构造器
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(context);
        //设置内容
        normalDialog.setMessage(string);
        //设置按钮
        normalDialog.setPositiveButton("确定"
                , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(i==0){
                            //挂失
                            defaultDialog=new DefaultDialog(context,"恭喜您挂失校园卡成功！\n挂失校园卡的后续功能正在开发中，敬请期待！");
                        }
                        else if(i==1){
                            //补办
                            Calendar c = Calendar.getInstance();
                            new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    defaultDialog=new DefaultDialog(context,"恭喜您预约成功！\n补办校园卡的后续处理功能正在开发中，敬请期待！");
                                }
                            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                        }
                        dialog.dismiss();
                    }
                });
        //创建并显示
        normalDialog.setNegativeButton("取消"
                , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        normalDialog.create().show();
    }
}
