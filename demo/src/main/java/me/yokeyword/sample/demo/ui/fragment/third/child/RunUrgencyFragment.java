package me.yokeyword.sample.demo.ui.fragment.third.child;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.sample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RunUrgencyFragment extends SupportFragment {
    TextView mTextView;


    public static RunUrgencyFragment newInstance() {
        RunUrgencyFragment fragment = new RunUrgencyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_run_urgency, container, false);
        initView(view, savedInstanceState);
        return view;
    }

    private void initView(View view, Bundle savedInstanceState) {
        mTextView = (TextView) view.findViewById(R.id.run_urgency_button);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop();
            }
        });
    }

    @Override
    public void onDestroyView() {
        Log.d("run", "======onDestroyView======");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("run", "======onDestroy======");
        super.onDestroy();
    }
}
