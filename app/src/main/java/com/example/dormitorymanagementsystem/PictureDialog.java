package com.example.dormitorymanagementsystem;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class PictureDialog {
    PictureDialog(final Context context, int resource){
        //创建dialog构造器
        final Dialog normalDialog = new Dialog(context);
        //设置布局
        normalDialog.setContentView(R.layout.dialog_picture);
        ImageView imageView =normalDialog.findViewById(R.id.dialogPicture);
        imageView.setImageResource(resource);
        normalDialog.setCanceledOnTouchOutside(true);
        Window w = normalDialog.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        normalDialog.onWindowAttributesChanged(lp);
        imageView.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        normalDialog.dismiss();
                        new DefaultDialog(context,"后续功能正在开发中，敬请期待！");
                    }
                });
        normalDialog.show();
    }
}
