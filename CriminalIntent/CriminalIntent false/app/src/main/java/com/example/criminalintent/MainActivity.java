package com.example.criminalintent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FragmentManager
        FragmentManager fm = getSupportFragmentManager();

        //向FragmentManager队列获取fragment事务
        androidx.fragment.app.Fragment fragment = fm.findFragmentById(R.id.fragment_container);//???跟书上不一样
        if (fragment == null) {//创建、添加、提交 fragment事务
            fragment = new CrimeFragment ();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)//activity_main.xml中的FrameLayout组件的资源ID。
                    .commit();
        }
    }
}