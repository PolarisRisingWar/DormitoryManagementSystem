package com.example.dormitorymanagementsystem;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {

    String studentId,student_name,in_year,phone,profession,institute,dorm_building,dorm_number;
    double card_balance=100.00;//校园卡余额
    double water_balance=21.11;//热水余额
    double electricity_balance=18.01;//电费余额
    double credit_score=130.6;//信用积分
    Button back;
    TextView studentID,studentName,inYear,phoneNumber,majority,college,dormBuilding,dormNumber;
    TextView cardBalance,waterBalance,electricityBalance,creditScore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        back=findViewById(R.id.backFromPersonalInfo);
        back.setOnClickListener(this);

        final SharedPreferences loginInfo=this.getSharedPreferences("loginInfo",0);
        studentId=loginInfo.getString("studentId","");
        student_name=loginInfo.getString("student_name","");
        in_year=loginInfo.getString("in_year","");
        phone=loginInfo.getString("phone","");
        profession=loginInfo.getString("profession","");
        institute=loginInfo.getString("institute","");
        dorm_building=loginInfo.getString("dorm_building","");
        dorm_number=loginInfo.getString("dorm_number","");

        studentID=findViewById(R.id.studentIDinPI);
        studentName=findViewById(R.id.studentName);
        inYear=findViewById(R.id.inYear);
        phoneNumber=findViewById(R.id.phoneNumber);
        majority=findViewById(R.id.majority);
        college=findViewById(R.id.college);
        dormBuilding=findViewById(R.id.dormBuilding);
        dormNumber=findViewById(R.id.dormNumber);

        cardBalance=findViewById(R.id.cardBalance);
        waterBalance=findViewById(R.id.waterBalance);
        electricityBalance=findViewById(R.id.electricityBalance);
        creditScore=findViewById(R.id.creditScore);

        studentID.setText(studentId);
        studentName.setText(student_name);
        inYear.setText(in_year);
        phoneNumber.setText(phone);
        majority.setText(profession);
        college.setText(institute);
        dormBuilding.setText(dorm_building);
        dormNumber.setText(dorm_number);

        cardBalance.setText(card_balance+"");
        waterBalance.setText(water_balance+"");
        electricityBalance.setText(electricity_balance+"");
        creditScore.setText(credit_score+"");
    }

    public void onClick(View v) {  //就业务逻辑来看，本activity应该只有返回一个按钮
        PersonalInfoActivity.this.finish();
    }
}
