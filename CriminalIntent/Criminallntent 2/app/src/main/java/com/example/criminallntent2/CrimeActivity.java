package com.example.criminallntent2;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected CrimeFragment createFragment() {
        return new CrimeFragment();
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //FragmentManager
        FragmentManager fm = getSupportFragmentManager();

        //向FragmentManager队列获取fragment事务
        androidx.fragment.app.Fragment fragment = fm.findFragmentById(R.id.fragment_container);//???跟书上不一样
        if (fragment == null) {//创建、添加、提交 fragment事务
            fragment = new CrimeFragment ();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)//activity_main.xml中的FrameLayout组件的资源ID。
                    .commit();
        }
    }*/
}