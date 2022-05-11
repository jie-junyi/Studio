package com.example.criminallntent2.CrimeLab;

import android.content.Context;

import com.example.criminallntent2.Crime.Bean.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    /**
     * crime数组对象将存储在一个单例里。单例是特殊的Java类，在创建实例时，一个单例类仅允
     * 许创建一个实例。应用能在内存里活多久，单例就能活多久。因此将对象列表保存在单例里的话，就能随时获
     * 取crime数据，不管activity和fragment的生命周期怎么变化。使用单例还要注意一点：Android从
     * 内存里清除应用时，单例对象也会随之消失。虽然CrimeLab单例不是数据持久保存的好方案，
     * 但它确实能保证仅拥有一份crime数据，并且能让控制器层类间的数据传递更容易。
     */
    private static CrimeLab sCrimeLab;//为啥是s开头
    private List<Crime> mCrimes;//泛型类，但不局限于泛型数组（ArrayList）

    //getCrimeLab方法
    public static CrimeLab get(Context context) {//静态方法
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
    //构造器方法
    private CrimeLab(Context context) {//私有的构造方法？？？
        mCrimes = new ArrayList<>();//泛型类
        //满载100个crime数据的模型层
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime # " + "("+(i+1)+")");
            crime.setSolved(false); // Every other one
            crime.setCallPolice(i % 5 == 0); //每隔两个个就是警方介入的crime，为0则报警
            mCrimes.add(crime);//添加
        }
    }
    //get,返回数组列表
    public List<Crime> getCrimes() {
        return mCrimes;
    }
    //返回带指定ID的Crime对象
    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

}
