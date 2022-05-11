package com.example.criminallntent2.Crime.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.criminallntent2.Crime.Bean.Crime;
import com.example.criminallntent2.CrimeLab.CrimeLab;
import com.example.criminallntent2.R;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {
    private static final String EXTRA_CRIME_ID=
            "com.bignerdranch.android.criminallntent.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager=(ViewPager) findViewById(R.id.activity_crime_pager_view_pager);

        mCrimes= CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager=getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                //这里的position为整型，表示要显示第几个ViewPager
                //等于CurrentItem

                //Crime crime=mCrimes.get(position+1);从0开始
                Crime crime=mCrimes.get(position);
                //托管CrimeFragment
                /**UUID crimeId = (UUID) getIntent()
                        .getSerializableExtra(EXTRA_CRIME_ID);*/
                //这样滑动就不会显示新内容
                //return CrimeFragment.newInstance(crimeId);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        for(int i =0;i<mCrimes.size();i++){
            if(mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);//跳页面
                break;
            }
        }
    }
    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent=new Intent(packageContext,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }
}
