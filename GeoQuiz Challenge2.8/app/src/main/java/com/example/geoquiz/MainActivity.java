package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;//自己修复的，Alt+enter
import android.widget.TextView;
import android.widget.Toast;
//引入Button对象

public class MainActivity extends AppCompatActivity {
    private Button mTureButton;//变量名，用来接收视图对象的
    private Button mFalseButton;//两个大写的，？？？命名错误会显示绿色
    private Button mNextButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };//显式的构造了对象。
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//里面写控制器
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view questions
        mQuestionTextView=(TextView) findViewById(R.id.question_text_view);
        updateQuestion();//直接调用，面向过程

        //false
        mFalseButton=(Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {//参数传过去的是括号的是全部代码
            @Override
            public void onClick(View view) {
                CheckAnswer(false);
            }
        });

        //ture
        mTureButton=(Button) findViewById(R.id.true_button);
        mTureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAnswer(true);
            }
        });

        //next
        mNextButton=(Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=mCurrentIndex+1;//加一就好
                updateQuestion();
            }
        });
    }
    //问题显式
    private void updateQuestion(){//公共代码部分，用一个方法写。
        int question=mQuestionBank[mCurrentIndex].getTextResId();//得到ID，
        mQuestionTextView.setText(question);//把id传入setText中
    }
    //更新逻辑判断，监听器
    private void CheckAnswer(boolean userPressTure){
        boolean answerIsTure=mQuestionBank[mCurrentIndex].isAnswerTure();
        int messageResId=0;
        if(userPressTure==answerIsTure)
            messageResId=R.string.correct_toast;
        else messageResId=R.string.incorrect_toast;
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }
}