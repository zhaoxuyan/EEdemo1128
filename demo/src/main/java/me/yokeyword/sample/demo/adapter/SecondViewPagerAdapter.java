package me.yokeyword.sample.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.yokeyword.sample.demo.ui.fragment.second.child.childpager.FirstPagerFragment;
import me.yokeyword.sample.demo.ui.fragment.second.child.childpager.OtherPagerFragment;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class SecondViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public SecondViewPagerAdapter(FragmentManager fm, String... titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return FirstPagerFragment.newInstance();
        } else{
            return OtherPagerFragment.newInstance();
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
