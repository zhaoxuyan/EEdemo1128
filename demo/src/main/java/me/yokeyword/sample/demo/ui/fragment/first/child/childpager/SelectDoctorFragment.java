package me.yokeyword.sample.demo.ui.fragment.first.child.childpager;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.sample.R;
import me.yokeyword.sample.demo.helper.DetailTransition;
import me.yokeyword.sample.demo.ui.fragment.first.child.DoctorFragment;

/**
 * Created by SyaoKyo on 2017/12/3.
 */

public class SelectDoctorFragment extends SupportFragment {
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //在这里定义Fragment的布局
        View view = inflater.inflate(R.layout.select_doctor, container, false);
        Button select_doctor = (Button) view.findViewById(R.id.select_doctor_btn);
        select_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( getParentFragment().getParentFragment() !=null)
                    ((SupportFragment) getParentFragment()).start(DoctorFragment.newInstance());
                else {
                    DoctorFragment fragment = DoctorFragment.newInstance();
                    // 这里是使用SharedElement的用例
                    // LOLLIPOP(5.0)系统的 SharedElement支持有 系统BUG， 这里判断大于 > LOLLIPOP
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                        setExitTransition(new Fade());
                        fragment.setEnterTransition(new Fade());
                        fragment.setSharedElementReturnTransition(new DetailTransition());
                        fragment.setSharedElementEnterTransition(new DetailTransition());

                        // 25.1.0以下的support包,Material过渡动画只有在进栈时有,返回时没有;
                        // 25.1.0+的support包，SharedElement正常
                        extraTransaction()
//                                .addSharedElement(((SecondPagerFragmentAdapter.VH) vh).img, getString(R.string.image_transition))
//                                .addSharedElement(((SecondPagerFragmentAdapter.VH) vh).tvTitle, "tv")
                                .start(fragment);
                    } else {
                        start(fragment);
                    }
                }
            }
        });
        return view;
    }
}
