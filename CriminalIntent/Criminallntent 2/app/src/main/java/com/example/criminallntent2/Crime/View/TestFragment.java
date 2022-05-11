package com.example.criminallntent2.Crime.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.criminallntent2.Callback;
import com.example.criminallntent2.R;

import java.util.Date;

public class TestFragment extends DialogFragment {
    private static final String ARG_DATE2 = "date2";
    public static final String EXTRA_DATE2 =
            "com.bignerdranch.android.criminalintent.date2";
    private Button mButton;
    private Callback mCallback;
    private EditText mEditText;
    private String mNickname;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.test_activity,container,false);
        Date date = (Date) getArguments().getSerializable(ARG_DATE2);
        mButton=(Button) view.findViewById(R.id.zhu_ce_over_8);
        mEditText=(EditText)view.findViewById(R.id.zhu_ce_over_7);
        mNickname=mEditText.getText().toString();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.SetName(mNickname);
                mCallback.dismiss();
                sendResult(Activity.RESULT_OK,date);
                Toast.makeText(getActivity(),"点击失败失败", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public static TestFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE2, date);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

   /*
   @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.test_activity,null);
        initView(view);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }*/

    private void initView(View view){
        Date date = (Date) getArguments().getSerializable(ARG_DATE2);
        mButton=(Button) view.findViewById(R.id.zhu_ce_over_8);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.dismiss();
                sendResult(Activity.RESULT_OK,date);
                Toast.makeText(getActivity(),"点击失败失败", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE2, date);
        getTargetFragment().
                onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


    //  实例化回调
    public void setCallback(Callback callback){
        this.mCallback = callback;
    }
}
