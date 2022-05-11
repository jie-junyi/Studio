package com.example.liuchuan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

public class MMMM extends StatusBar {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置状态栏透明
        StatusBar_to_transparent(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);

    }
}
