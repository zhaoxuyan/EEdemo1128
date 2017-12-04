package me.yokeyword.sample.demo.ui.fragment.first.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import me.yokeyword.sample.R;
import me.yokeyword.sample.demo.MapActivity;
import me.yokeyword.sample.demo.base.BaseBackFragment;

/**
 * Created by SyaoKyo on 2017/10/20.
 */

public class DoctorFragment extends BaseBackFragment {
    private View mView;
    private RelativeLayout fujinshequyiyuan;

    public static DoctorFragment newInstance() {
        DoctorFragment fragment = new DoctorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_doctor, container, false);
        mView = view;
        fujinshequyiyuan = (RelativeLayout) mView.findViewById(R.id.fujinshequyiyuan);
        fujinshequyiyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//
//    }
//
//    public static DoctorFragment newInstance(String content) {
//        Bundle args = new Bundle();
//        args.putString("ARGS", content);
//        DoctorFragment fragment = new DoctorFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
}
