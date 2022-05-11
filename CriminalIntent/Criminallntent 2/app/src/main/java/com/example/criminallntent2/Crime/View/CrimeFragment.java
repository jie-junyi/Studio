package com.example.criminallntent2.Crime.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.criminallntent2.Callback;
import com.example.criminallntent2.Crime.Bean.Crime;
import com.example.criminallntent2.CrimeLab.CrimeLab;
import com.example.criminallntent2.R;

import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment implements Callback {
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_DATE2 = "DialogDate2";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST = 1;

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private Button mTestButton;
    private EditText mEditText;
    private CheckBox mSolvedCheckBox;
    private TestFragment test;
    private TextView mTextView;

    @Override //输入OnCreate即可自动补全
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_crime);????????
//        mCrime=new Crime();
        //UUID crimeId = (UUID) getActivity().getIntent()
        //.getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        //用CrimeLab根据UUID来得到一个Crime对象
        UUID crimeId = (UUID) getArguments()
                .getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity())
                .getCrime(crimeId);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        //EditText
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        //显示
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());//setTitle
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });//匿名内部类

        //DateButton
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        updateDate();//Button也可以显示文字
        //mDateButton.setEnabled(false);//只能按一次
        //AlertDialog对话框
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);

            }
        });
        //
        //TestButton
        mTestButton = (Button) v.findViewById(R.id.crime_date2);
        //updateDate();//Button也可以显示文字
        //mDateButton.setEnabled(false);//只能按一次
        //AlertDialog对话框
        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager2 = getFragmentManager();
                test = TestFragment.
                        newInstance(mCrime.getDate());
                test.setTargetFragment(CrimeFragment.this, REQUEST);
                //设置回调，子拿父
                test.setCallback(CrimeFragment.this);
                test.show(manager2, DIALOG_DATE2);
            }

        });
        mEditText = (EditText) v.findViewById(R.id.zhu_ce_over_7);

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        //显示
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
        mTextView = (TextView) v.findViewById(R.id.dddd);


        return v;
    }

    //接收回调信息
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        //控制回调正确or多选
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
        if (requestCode == REQUEST) {
            Date date = (Date) data
                    .getSerializableExtra(TestFragment.EXTRA_DATE2);
            mCrime.setDate(date);
            updateDate();
        }

    }

    //重复代码变成私有方法
    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //回调方法实现
    //在父亲中写，子调用
    //本质为父亲的东西
    @Override
    public void dismiss() {
        test.dismiss();
    }

    @Override
    public void SetName(String string) {
        mTextView.setText(string);
    }
}
