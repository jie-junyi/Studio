package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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
    private static final String KEY_ANSWER="answer";
    private static final String KEY_CHEATER="cheat";
    private static final String KEY_CHEAT_SEVERAL="several";
    private static final int REQUEST_CODE_CHEAT = 0;//定义请求代码
    private Button mTureButton;//变量名，用来接收视图对象的
    private Button mFalseButton;//两个大写的，？？？命名错误会显示绿色
    private Button mCheatButton;
    private Button mNextButton;
    private Button mLastButton;
    private TextView mSeveral;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };//显式的构造了对象。

    private int mCheatSeveral=3;
    private int mCurrentIndex = 0;
    private boolean [] mIsCheater=new boolean [mQuestionBank.length];

    @Override//注释，编译器里面要有这个方法，可以帮忙检查方法名是否错误
    protected void onCreate(Bundle savedInstanceState) {//里面写控制器
        Log.d(TAG,"onCreate(Bundle) called");//日志记录方法
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //接收保存的数据（当前第几题、作弊次数、是否作弊数组）
        if(savedInstanceState!=null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);//0是什么意思？？？，是mCurrentIndex的现有值？？？
            mCheatSeveral=savedInstanceState.getInt(KEY_CHEAT_SEVERAL,3);
            mIsCheater=savedInstanceState.getBooleanArray(KEY_CHEATER);
            boolean[]answerList=savedInstanceState.getBooleanArray(KEY_ANSWER);
            for(int i=0;i<mQuestionBank.length;i++){
                mQuestionBank[i].setAnswer(answerList[i]);
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

        //Several TextView
        mSeveral = (TextView)findViewById(R.id.Several);
        updateSeveral();

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

        //cheat
        mCheatButton=(Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheatSeveral--;
                updateSeveral();
                //Intent intent=new Intent(MainActivity.this,CheatActivity.class);//实例intent对象
                boolean answerIsTure=mQuestionBank[mCurrentIndex].isAnswerTure();
                Intent intent=CheatActivity.newIntent(MainActivity.this,answerIsTure);//封装
                //startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE_CHEAT);//传入Intent，并加上请求代码
            }
        });

        //next
        mNextButton=(Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex++;//加一就好
                updateQuestion();
                ButtonEnabled();
            }
        });

        //last
        mLastButton=(Button) findViewById(R.id.last_button);
        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex--;
                updateQuestion();
                ButtonEnabled();
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
    //保存数据
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){//系统会在onStop之前调用该方法
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"saveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
        savedInstanceState.putInt(KEY_CHEAT_SEVERAL,mCheatSeveral);
        savedInstanceState.putBooleanArray(KEY_CHEATER,mIsCheater);
        boolean []answerList=new boolean[mQuestionBank.length];
        for(int i=0;i<mQuestionBank.length;i++){
            answerList[i]=mQuestionBank[i].isAnswer();
        }
        savedInstanceState.putBooleanArray(KEY_ANSWER,answerList);
    }

    //系统自动调用，接收儿子传来的信息
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);//要有父亲
       if (resultCode != Activity.RESULT_OK) {
           return;
       }
       if (requestCode == REQUEST_CODE_CHEAT) {
           if (data == null) {
               return;
           }
           //最终接收是否为作弊者，且调用子类方法，来获取extra信息
           mIsCheater[mCurrentIndex] = CheatActivity.wasAnswerShown(data);
       }
   }



    //问题显式
    private void updateQuestion(){
        int question=mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);//把id传入setText中
        //很奇怪，为什么在这里写ButtonEnabled()会出问题;
    }
    //作弊次数,公共代码
    private void updateSeveral(){
        //mCheatSeveral--;
        if(mCheatSeveral>0)
            mSeveral.setText("剩下"+mCheatSeveral+"次机会");
        else if(mCheatSeveral==0) {
            mSeveral.setText("已经没有机会了");
            mCheatButton.setEnabled(false);
        }
    }
    //更新逻辑判断，监听器（显示回答是否正确以及确保已回答的问题不可再回答）
    private void CheckAnswer(boolean userPressTure){
        boolean answerIsTure=mQuestionBank[mCurrentIndex].isAnswerTure();
        int messageResId=0;
        if(mIsCheater[mCurrentIndex]){
            messageResId=(R.string.judgment_toast);
        }
        else if (userPressTure==answerIsTure) {
            messageResId = R.string.correct_toast;
        }
        else messageResId=R.string.incorrect_toast;
        mQuestionBank[mCurrentIndex].setAnswer(true);
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
        ButtonEnabled();

    }

    public void ButtonEnabled(){//setEnabled()方法？？？？，
        if(mQuestionBank[mCurrentIndex].isAnswer()){
            mTureButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }else{
            mTureButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }

}