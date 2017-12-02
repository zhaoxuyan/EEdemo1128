package me.yokeyword.sample.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.yokeyword.sample.demo.ui.fragment.first.child.childpager.FamilyMemberPagerFragment;
import me.yokeyword.sample.demo.ui.fragment.first.child.childpager.MyPagerFragment;

/**
 * Created by zhaoxuyan on 2017/12/2.
 */

public class FirstViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public FirstViewPagerAdapter(FragmentManager fm, String... titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return MyPagerFragment.newInstance();
        } else{
            return FamilyMemberPagerFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
