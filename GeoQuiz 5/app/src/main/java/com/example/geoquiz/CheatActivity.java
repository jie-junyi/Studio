package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

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
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_SHOWN,mCheatState);
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTure){
        Intent intent=new Intent(packageContext,CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTure);//可以写多个extra
        //键在CheatActivity中
        return intent;
    }
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent date=new Intent();
        date.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,date);//结果代码，RESULT_OK
    }
   // public boolean wasAnswerShown(Intent result){!!!
        //return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
   // }
    //静态的！！！
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);//返回值
    }
}