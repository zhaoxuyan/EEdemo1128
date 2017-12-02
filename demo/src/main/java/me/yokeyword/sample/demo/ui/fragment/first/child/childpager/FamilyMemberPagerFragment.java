package me.yokeyword.sample.demo.ui.fragment.first.child.childpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.sample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyMemberPagerFragment extends Fragment {


    public static FamilyMemberPagerFragment newInstance() {

        Bundle args = new Bundle();

        FamilyMemberPagerFragment fragment = new FamilyMemberPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.zhihu_fragment_first_familymenber_pager, container, false);
    }

}
