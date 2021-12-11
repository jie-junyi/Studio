package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;//自己修复的，Alt+enter
import android.widget.TextView;
import android.widget.Toast;
//引入Button对象

public class MainActivity extends AppCompatActivity {
    //声明部分
    private static final String TAG="MainActivity";//定义一个常量，日志来源
    private static final String KEY_INDEX="index";//定义一个键
    private static final String KEY_ANSWER="KEY_ANSWER";
    private Button mTureButton;//变量名，用来接收视图对象的
    private Button mFalseButton;//两个大写的，？？？命名错误会显示绿色
    private Button mNextButton;
    private Button mLastButton;
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

    @Override//注释，编译器里面要有这个方法，可以帮忙检查方法名是否错误
    protected void onCreate(Bundle savedInstanceState) {//里面写控制器
        Log.d(TAG,"onCreate(Bundle) called");//日志记录方法
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);//0是什么意思？？？，是mCurrentIndex的现有值？？？
            int[] answerList=savedInstanceState.getIntArray(KEY_ANSWER);
            for(int i=0;i<mQuestionBank.length;i++){
                mQuestionBank[i].setIsAnswered(answerList[i]);//将答题情况存储在question中
            }
        }

        //view questions
        mQuestionTextView=(TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {//点击问题框也会进入下一题
            @Override
            public void onClick(View view) {
                mCurrentIndex=mCurrentIndex+1;
                updateQuestion();
            }
        });

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
                mCurrentIndex++;//加一就好
                updateQuestion();
            }
        });

        //last
        mLastButton=(Button) findViewById(R.id.last_button);
        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex--;
                updateQuestion();
            }
        });
    }
    //重写五个生命周期的方法onStart、onResume、onPause、onStop、onDestroy
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){//系统会在onStop之前调用该方法
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"onSaveInstanceState() called");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);

        int[] answerList=new int[mQuestionBank.length];//存储是否已经答过该题的情况
        for(int i=0;i<answerList.length;i++){
            answerList[i]=mQuestionBank[i].getIsAnswered();
        }
        savedInstanceState.putIntArray(KEY_ANSWER,answerList);
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
        if(userPressTure==answerIsTure) {
            messageResId = R.string.correct_toast;
            mQuestionBank[mCurrentIndex].setIsAnswered(1);
        }
        else {
            messageResId = R.string.incorrect_toast;
            mQuestionBank[mCurrentIndex].setIsAnswered(0);
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }
    //
    public void ButtonEnabled(){//setEnabled()方法？？？？
        if(mQuestionBank[mCurrentIndex].getIsAnswered()>0){
            mTureButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }else{
            mTureButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }
}