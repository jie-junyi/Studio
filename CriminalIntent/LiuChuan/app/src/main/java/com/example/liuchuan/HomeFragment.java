package com.example.liuchuan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private ImageButton mImageButton1;
    private ImageButton mImageButton2;
    private ImageButton mImageButton3;
    private ImageButton mImageButton4;
    private ImageButton mImageButton5;
    private ImageButton mImageButton6;
    private ImageButton mImageButton7;
    private ImageButton mImageButton8;
    private ImageButton mImageButton9;
    private ImageButton mImageButton10;
    private ImageButton mImageButton11;
    private ImageButton mImageButton12;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home, container, false);

        mImageButton1 = (ImageButton) view.findViewById(R.id.Hbtn1);
        mImageButton1.setBackgroundResource(R.mipmap.shousuo1);

        mImageButton2 = (ImageButton) view.findViewById(R.id.Hbtn2);
        mImageButton2.setBackgroundResource(R.mipmap.shousuo2);

        mImageButton3 = (ImageButton) view.findViewById(R.id.Hbtn3);
        mImageButton3.setBackgroundResource(R.mipmap.jia);

        mImageButton4 = (ImageButton) view.findViewById(R.id.Hbtn4);
        mImageButton4.setBackgroundResource(R.mipmap.qujian2);

        mImageButton5 = (ImageButton) view.findViewById(R.id.Hbtn5);
        mImageButton5.setBackgroundResource(R.mipmap.jijian2);

        mImageButton6 = (ImageButton) view.findViewById(R.id.Hbtn6);
        mImageButton6.setBackgroundResource(R.mipmap.shenfenma2);

        mImageButton7 = (ImageButton) view.findViewById(R.id.Hbtn7);
        mImageButton7.setBackgroundResource(R.mipmap.daiqu);

        mImageButton8 = (ImageButton) view.findViewById(R.id.Hbtn8);
        mImageButton8.setBackgroundResource(R.mipmap.ziqu);

        mImageButton9 = (ImageButton) view.findViewById(R.id.Hbtn9);
        mImageButton9.setBackgroundResource(R.mipmap.shangmen);

        mImageButton10 = (ImageButton) view.findViewById(R.id.Hbtn10);
        mImageButton10.setBackgroundResource(R.mipmap.zhaoping);

        mImageButton11 = (ImageButton) view.findViewById(R.id.Hbtn11);
        mImageButton11.setBackgroundResource(R.mipmap.history);


        return view;
    }
}
