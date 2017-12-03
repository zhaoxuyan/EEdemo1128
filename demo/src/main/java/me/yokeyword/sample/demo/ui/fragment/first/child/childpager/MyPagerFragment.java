package me.yokeyword.sample.demo.ui.fragment.first.child.childpager;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.sample.R;
import me.yokeyword.sample.demo.helper.DetailTransition;
import me.yokeyword.sample.demo.ui.fragment.CycleFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPagerFragment extends SupportFragment {
    private CardView user_card;
    private CardView community_card;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_first_my_pager, container, false);
//        这段代码加上会报错，不知道原因
//        EventBusActivityScope.getDefault(_mActivity).register(this);
        initView(view);
        return view;
    }
    private void initView(View view) {
        user_card = (CardView)view.findViewById(R.id.user_card);
        community_card = (CardView)view.findViewById(R.id.community_card);
        user_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( getParentFragment().getParentFragment() !=null)
                    ((SupportFragment) getParentFragment()).start(CycleFragment.newInstance(1));
                else {
                    CycleFragment fragment = CycleFragment.newInstance(1);
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
    }



    public static MyPagerFragment newInstance() {

        Bundle args = new Bundle();

        MyPagerFragment fragment = new MyPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
