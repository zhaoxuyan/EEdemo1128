package me.yokeyword.sample.demo.ui.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.sample.R;
import me.yokeyword.sample.demo.base.BaseBackFragment;

import static android.R.attr.fragment;

/**
 * Created by SyaoKyo on 2017/12/3.
 */

public class DoctorFragment extends BaseBackFragment  {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);
        return view;
    }

    public static DoctorFragment newInstance() {

        Bundle args = new Bundle();
        DoctorFragment fragment = new DoctorFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
