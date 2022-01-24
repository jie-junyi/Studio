package com.example.criminallntent2;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;//import android.app.Fragment;作怪！！！

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);//代码实现
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    //方法,关联RecyclerView和Adapter
    private void updateUI(){
        CrimeLab crimeLab=CrimeLab.get(getActivity());//获取单例类
        List<Crime> crimes=crimeLab.getCrimes();//获取单例类中的数据
        //创建一个包含所有crimes对象数据的adapter，会调用内部的三个函数
        mAdapter=new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    //内部类CrimeHolder
    private class CrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener  {//匿名内部类，实现一个监听接口
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private Crime mCrime;


        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);//???，（点击整个都可响应）
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);//实例化组件
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);//实例化组件
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
        }
        //新的Crime要在CrimeHolder中显示时 /*只要收到一个crime，CrimeHolder就会刷新显示mTitleTextView和mDateTextView*/
        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());//使用模型层的数据
            //mDateTextView.setText(mCrime.getDate().toString());
            String date=(String) DateFormat.format("EEEE, MMM dd, yyyy", mCrime.getDate());
            mDateTextView.setText(date);
            mSolvedImageView.setVisibility(crime.isSolved() ? View.GONE:View.VISIBLE );//我自己反过来了！！！
        }

        @Override//监听方法
        public void onClick(View v) {
            //getActivity是什么？？？---Context对象
            //CrimeListFragment是通过使用getActivity()方法传
            //入它的托管activity来满足的
            Intent intent = new Intent(getActivity(), CrimeActivity.class);
            startActivity(intent);
        }
    }

    //内部类callPoliceCrimeHolder
    private class callPoliceCrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        //绑定列表项前需要实例化布局内的相关组件
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;
        private Button mCallPolice;

        public callPoliceCrimeHolder(LayoutInflater inflater,ViewGroup parent){
            //首先实例化 list_item_call_crime 布局
            // 然后传给super（）即ViewHolder的构造方法*/
            super(inflater.inflate(R.layout.list_item_call_crime,parent,false));
            //实例化布局内的相关组件
            mTitleTextView=(TextView)itemView.findViewById(R.id.crime_title);
            mDateTextView=(TextView) itemView.findViewById(R.id.crime_date);
            mCallPolice=(Button)itemView.findViewById(R.id.call_police);
            //点击CallPolice可响应
            mCallPolice.setOnClickListener(this);
        }

        //只要收到一个crime，CrimeHolder就会刷新显示mTitleTextView和mDateTextView*/
        public void bind(Crime crime){
            mCrime=crime;
            mTitleTextView.setText(mCrime.getTitle());
            //mDateTextView.setText(mCrime.getDate().toString());
            String date=(String) DateFormat.format("EEEE, MMM dd, yyyy", mCrime.getDate());
            mDateTextView.setText(date);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),"you call 110",Toast.LENGTH_SHORT).show();
        }
    }

    //内部类CrimeAdapter
    private class CrimeAdapter extends RecyclerView.Adapter{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes=crimes;
        }

        //增加视图类别功能
        @Override
        public int getItemViewType(int position) {
            //为真不报警
            if (mCrimes.get(position).isCallPolice()){
                return 1;
            }else {
                return 0;
            }
        }

        //当需要一个新的ViewHolder来显示列表项时，会调用这个
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //先创建LayoutInflater
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());

            //判断上面的viewType的值判断使用什么布局，即getItemViewType的返回值
            if (viewType == 0) {
                return new CrimeHolder(layoutInflater, parent);
            } else {
                return new callPoliceCrimeHolder(layoutInflater, parent);
            }
        }

        //需要绑定数据（上下滑动屏幕）时，就会读取当前位置并绑定*/
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof CrimeHolder){
                Crime crime=mCrimes.get(position);
                ((CrimeHolder)holder).bind(crime);
            }else if (holder instanceof callPoliceCrimeHolder){
                Crime crime=mCrimes.get(position);
                ((callPoliceCrimeHolder)holder).bind(crime);
            }
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

}


