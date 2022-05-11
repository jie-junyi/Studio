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

public class MainActivity extends StatusBar implements View.OnClickListener {
    private ImageButton mHome;
    private ImageButton mBuy;
    private ImageButton mMy;
    private ViewPager mViewPager;
    private List<Fragment> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置状态栏透明
        //StatusBar_to_transparent(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ViewPager
        mHome = (ImageButton) findViewById(R.id.Abtn1);
        mHome.setBackgroundResource(R.mipmap.home4);
        mHome.setOnClickListener(this);

        mBuy = (ImageButton) findViewById(R.id.Abtn2);
        mBuy.setBackgroundResource(R.mipmap.buy1);
        mBuy.setOnClickListener(this);

        mMy = (ImageButton) findViewById(R.id.Abtn3);
        mMy.setBackgroundResource(R.mipmap.my1);
        mMy.setOnClickListener(this);

        mViewPager = (ViewPager) findViewById(R.id.View_Pager);
        mList = new ArrayList<>();
        mList.add(new HomeFragment());
        mList.add(new BuyFragment());
        mList.add(new MyFragment());


        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                //返回的是一个实例（fragment实例或者.......）
                //可以拿一个泛型数组来实现
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
            //删除container中指定下标的视图
            //?????是否需要
            /** @Override public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
            Fragment fragment = mList.get(position);
            fragmentManager.beginTransaction().hide(fragment).commit();
            }*/
        });
        //设置当前页面
        mViewPager.setCurrentItem(0);
        //设置最多跳转
        mViewPager.setOffscreenPageLimit(2);
        //点击
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mHome.setBackgroundResource(R.mipmap.home4);
                        mBuy.setBackgroundResource(R.mipmap.buy1);
                        mMy.setBackgroundResource(R.mipmap.my1);
                        break;
                    case 1:
                        mHome.setBackgroundResource(R.mipmap.home1);
                        mBuy.setBackgroundResource(R.mipmap.buy2);
                        mMy.setBackgroundResource(R.mipmap.my1);
                        break;
                    case 2:
                        mHome.setBackgroundResource(R.mipmap.home1);
                        mBuy.setBackgroundResource(R.mipmap.buy1);
                        mMy.setBackgroundResource(R.mipmap.my2);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //要有implements View.OnClickListener
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Abtn1:
                //点击"健康"时切换到第一页
                mViewPager.setCurrentItem(0);
                mHome.setBackgroundResource(R.mipmap.home2);
                mBuy.setBackgroundResource(R.mipmap.buy1);
                mMy.setBackgroundResource(R.mipmap.my1);
                break;
            case R.id.Abtn2:
                //点击"健康"时切换到第一页
                mViewPager.setCurrentItem(1);
                mHome.setBackgroundResource(R.mipmap.home1);
                mBuy.setBackgroundResource(R.mipmap.buy2);
                mMy.setBackgroundResource(R.mipmap.my1);
                break;
            case R.id.Abtn3:
                //点击"健康"时切换到第一页
                mViewPager.setCurrentItem(2);
                mHome.setBackgroundResource(R.mipmap.home1);
                mBuy.setBackgroundResource(R.mipmap.buy1);
                mMy.setBackgroundResource(R.mipmap.my2);
                break;
        }
    }
}
