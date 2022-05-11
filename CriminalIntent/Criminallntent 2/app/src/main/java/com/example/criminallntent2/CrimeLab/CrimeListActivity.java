package com.example.criminallntent2.CrimeLab;

import com.example.criminallntent2.SingleFragmentActivity;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected CrimeListFragment createFragment() {
        return new CrimeListFragment();
    }
}
