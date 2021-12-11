package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;//自己修复的，Alt+enter
import android.widget.TextView;
import android.widget.Toast;
//引入Button对象

public class MainActivity extends AppCompatActivity {
    private Button mTureButton;//变量名，用来接收视图对象的
    private Button mFalseButton;//两个大写的，？？？命名错误会显示绿色


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view question


        //false
        mFalseButton=(Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {//参数传过去的是括号的是全部代码
            @Override
            public void onClick(View view) {
                Toast o=Toast.makeText(MainActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT);//也可分开写，，，LENGTH_LONG？长时间？
                //o.setGravity(Gravity.TOP,0,0);
                o.show();
            }
        });
        //ture
        mTureButton=(Button) findViewById(R.id.true_button);
        mTureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t=Toast.makeText(MainActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT);
                //t.setGravity(Gravity.TOP,0,0);
                t.show();
            }
        });
    }
}