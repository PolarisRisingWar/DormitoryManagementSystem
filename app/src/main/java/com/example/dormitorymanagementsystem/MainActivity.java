package com.example.dormitorymanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
    String TAG="MainActivity";

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
                        break;
                    case R.id.dorm_button:
                        go2DormFragment();
                        break;
                    case R.id.my_button:
                        go2MyFragment();
                        break;
                    default:
                        break;
                }
            }

        });
    }

    public void onBackPressed(){//这是为了在按返回键后返回寝室事务界面。
        // 不知道为什么，这么写的话，从我的生活或个人中心跳转以后就不能点击原按钮返回
        //嗯，我认为，上一句注释的原因是，用户对radiogroup的选择没有改变，所以再次点击原按钮不会被switch监听到
        /*life_btn.setBackgroundResource(R.drawable.shape2);
        dorm_btn.setBackgroundResource(R.drawable.shape2);
        my_btn.setBackgroundResource(R.drawable.shape2);
        go2DormFragment();*/

        //既然如此那就只能原地重来了
        //onCreate(null);
        //这个直接闪退了

        //我整了这么个方法……但是那个转场动画看得不舒服，这明显就不是后退而是整了个新activity
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);

        //overridePendingTransition(0, 0);
        //这一句可以把那个转场动画关闭，但是会闪屏，瞎了
        //就那个转场动画，凑活着用吧
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
        life_btn.setBackgroundResource(R.drawable.shape1);
    }

    public void go2DormFragment() {    //去寝室事务界面
        fragmentTransaction=fragmentManager.beginTransaction();
        defaultHide();
        fragmentTransaction.show(dormFragment).commit();
        dorm_btn.setBackgroundResource(R.drawable.shape1);
    }

    public void go2MyFragment() {    //去个人中心界面
        fragmentTransaction=fragmentManager.beginTransaction();
        defaultHide();
        fragmentTransaction.show(myFragment).commit();
        my_btn.setBackgroundResource(R.drawable.shape1);
    }

    public void go2LoginFragment(){
        fragmentTransaction=fragmentManager.beginTransaction();
        defaultHide();
        fragmentTransaction.show(loginFragment).commit();
    }
}
