package com.example.dormitorymanagementsystem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    //主页面，有三个按钮，我的生活、寝室事务、个人中心
    //分别跳转至三个fragment

    //LifeFragment lifeFragment;
    //5.30 night WeatherFragment weatherFragment;
    private FragmentManager fragmentManager;
    //private Fragment fragments[]=new Fragment[2];
    private FragmentTransaction fragmentTransaction;
    private RadioButton life_btn,dorm_btn,my_btn;
    RadioGroup radioGroup;//用这个radiogroup来装三个radiobutton

    //5.31：我现在的想法是把所有要用的fragment全部添加到mainactivity上，在fragment里要用主页方法的话调用getActivity()
    //后者以后再试。相信我可以的
    //注意添加fragment步骤：在此处定义，在底下实例化，add进事务，然后才能hide或show或怎样。要改每一个hide
    LifeFragment lifeFragment;
    MyFragment myFragment;
    DormFragment dormFragment;

    LoginFragment loginFragment;

    WeatherFragment weatherFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*5.30night fragmentManager=getSupportFragmentManager();
        fragments[0]=fragmentManager.findFragmentById(R.id.fragment_life);
        fragmentTransaction=fragmentManager.beginTransaction().hide(fragments[0]);
        fragmentTransaction.show(fragments[0]).commit();*/

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        lifeFragment=new LifeFragment();
        myFragment=new MyFragment();
        dormFragment=new DormFragment();
        loginFragment=new LoginFragment();

        fragmentTransaction.add(R.id.container,lifeFragment).
                add(R.id.container,dormFragment).
                add(R.id.container,myFragment).
                add(R.id.container,loginFragment);

        defaultHide();
        fragmentTransaction.show(dormFragment).commit();//每个fragmentTransaction只能commit一次
        // 但似乎重新实例化fragmentTransaction还会保留原来的片段

        life_btn=findViewById(R.id.life_button);
        dorm_btn=findViewById(R.id.dorm_button);
        my_btn=findViewById(R.id.my_button);
        radioGroup=findViewById(R.id.bottomGroup);

        life_btn.setBackgroundResource(R.drawable.shape2);
        //shape1是橙色背景，shape2是白色背景
        dorm_btn.setBackgroundResource(R.drawable.shape1);
        my_btn.setBackgroundResource(R.drawable.shape2);
        /*5.31 morninglife_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go2LifeFragment();
            }
        });*/

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                life_btn.setBackgroundResource(R.drawable.shape2);
                dorm_btn.setBackgroundResource(R.drawable.shape2);
                my_btn.setBackgroundResource(R.drawable.shape2);
                switch (checkedId){
                    case R.id.life_button:
                        go2LifeFragment();
                        life_btn.setBackgroundResource(R.drawable.shape1);
                        break;
                    case R.id.dorm_button:
                        go2DormFragment();
                        dorm_btn.setBackgroundResource(R.drawable.shape1);
                        break;
                    case R.id.my_button:
                        go2MyFragment();
                        my_btn.setBackgroundResource(R.drawable.shape1);
                        break;
                    default:
                        break;
                }
            }

        });
    }

    public void defaultHide(){
        fragmentTransaction.hide(lifeFragment).hide(dormFragment).hide(myFragment).hide(loginFragment);
    }
    public void go2LifeFragment() {    //去我的生活页面
        /*5.30night fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        weatherFragment= new WeatherFragment();
        fragmentTransaction.replace(R.id.container, weatherFragment);
        fragmentTransaction.commit();*/

        fragmentTransaction=fragmentManager.beginTransaction();
        defaultHide();
        fragmentTransaction.show(lifeFragment).commit();
    }

    public void go2DormFragment() {    //去寝室事务界面
        fragmentTransaction=fragmentManager.beginTransaction();
        defaultHide();
        fragmentTransaction.show(dormFragment).commit();
    }

    public void go2MyFragment() {    //去寝室事务界面
        fragmentTransaction=fragmentManager.beginTransaction();
        defaultHide();
        fragmentTransaction.show(myFragment).commit();
    }

    public void go2LoginFragment(){
        fragmentTransaction=fragmentManager.beginTransaction();
        defaultHide();
        fragmentTransaction.show(loginFragment).commit();
    }
}
