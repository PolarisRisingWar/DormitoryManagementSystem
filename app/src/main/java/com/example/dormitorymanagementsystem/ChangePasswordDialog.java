package com.example.dormitorymanagementsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordDialog implements Runnable{    //当在个人中心界面点击“修改密码”按钮时跳出此弹窗
    EditText former,later;
    DefaultDialog defaultDialog;
    String newpwd,studentID;
    SqlHelper sqlHelper;

    ChangePasswordDialog(final Context context, String studentId, final String password){
        studentID=studentId;
        AlertDialog.Builder a_dialog = new AlertDialog.Builder(context);
        a_dialog.setTitle("修改密码");
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_change_password, null);
        a_dialog.setView(view);

        a_dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //确定操作的内容
                        former=view.findViewById(R.id.former_password);
                        if(former.getText().toString().equals(password)){
                            later=view.findViewById(R.id.new_password);
                            newpwd=later.getText().toString();
                            if (newpwd==""||newpwd==null){
                                defaultDialog=new DefaultDialog(context,"请您输入正确的新密码！");
                            }
                            else if(newpwd.equals(password)){
                                defaultDialog=new DefaultDialog(context,"新密码不能与原密码相同！");
                            }
                            else {
                                Thread sql_update=new Thread(ChangePasswordDialog.this);
                                sql_update.start();
                                defaultDialog=new DefaultDialog(context,"您的密码已经成功被改变了！");
                                //这里有个bug：此时sharedpreference中存储的仍然是改变前的密码
                                //但是重新登录时用新密码，就能置换sharedpreference中的password
                            }
                        }
                        else {
                            defaultDialog=new DefaultDialog(context,"请您输入正确的密码！");
                        }
                    }
                });

        a_dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        a_dialog.show();
    }

    public void run() {
        String sql = "update student set secret_code='"+newpwd+"' where student_id='"+studentID+"';";
        sqlHelper=new SqlHelper();
        sqlHelper.onUpdate(sql);
        sqlHelper.onFinish();
    }
}
