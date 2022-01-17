package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator; //
import android.animation.AnimatorListenerAdapter; //
import android.content.Context;
import android.content.Intent;
import android.os.Build;    //
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils; //
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
    private static final String KEY_SHOWN="shown";//如何选定要保留的值！！！

    private boolean mAnswerIsTure;
    private boolean mCheatState;

    private TextView mTextView;
    private Button mShowButton;
    private TextView mTextView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        //显示API
        mTextView2=(TextView) findViewById(R.id.show_apl_level);
        CharSequence cs = "API Level " + Build.VERSION.SDK_INT;
        mTextView2.setText(cs);

        //接收保存的数据
        if(savedInstanceState!=null) {
            mCheatState = savedInstanceState.getBoolean(KEY_SHOWN, false);
        }
        if(mCheatState){
            setAnswerShownResult(true);
        }

        mAnswerIsTure=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);//false,为键为空的时候
        mTextView=(TextView) findViewById(R.id.answer_text);

        mShowButton=(Button) findViewById(R.id.show_answer_button);
        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheatState=true;
                if(mAnswerIsTure)
                    mTextView.setText(R.string.true_button);
                else
                    mTextView.setText(R.string.false_button);
                setAnswerShownResult(true);
                //
                int cx = mShowButton.getWidth() / 2;
                int cy = mShowButton.getHeight() / 2;
                float radius = mShowButton.getWidth();
                Animator anim = ViewAnimationUtils
                        .createCircularReveal(mShowButton, cx, cy, radius, 0);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mShowButton.setVisibility(View.INVISIBLE);
                    }
                });
                anim.start();
                //
            }
        });
    }
    //保存数据
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_SHOWN,mCheatState);
    }

    //从父亲传入的方法，写在儿子中
    public static Intent newIntent(Context packageContext, boolean answerIsTure){
        Intent intent=new Intent(packageContext,CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTure);//Extra可以携带信息，可以写多个extra
        //键在CheatActivity中
        return intent;
    }
    //传信息给父亲的方法，自己用
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent date=new Intent();
        date.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,date);//传入Intent对象和结果代码，RESULT_OK给父亲
    }
    //父用来获取extra信息，因为定义的extra在子类中
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);//返回值
    }
}