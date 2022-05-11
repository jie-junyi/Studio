package com.example.criminallntent2.Crime.View;

import android.content.Context;
import android.content.Intent;

import com.example.criminallntent2.SingleFragmentActivity;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
    /**public其它类不会用到，所以改为私有，封装*/
    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";

    @Override
    protected CrimeFragment createFragment() {
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
    //静态方法
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

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
    }*/
}