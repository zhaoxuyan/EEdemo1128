package me.yokeyword.sample.demo.ui.fragment.first.child.childpager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.sample.R;
import me.yokeyword.sample.demo.helper.DetailTransition;
import me.yokeyword.sample.demo.ui.fragment.CycleFragment;
import me.yokeyword.sample.demo.ui.fragment.first.ViewPagerCards.CardItem;
import me.yokeyword.sample.demo.ui.fragment.first.ViewPagerCards.CardPagerAdapter;
import me.yokeyword.sample.demo.ui.fragment.first.ViewPagerCards.ClickableViewPager;
import me.yokeyword.sample.demo.ui.fragment.first.ViewPagerCards.ShadowTransformer;
import me.yokeyword.sample.demo.ui.fragment.first.child.DoctorFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPagerFragment extends SupportFragment implements CompoundButton.OnCheckedChangeListener {
    private CardView user_card;
    private CardView community_card;
    private Button button;

    // Viewpager既可以是view 也可以是fragment 牛逼！
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
//    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;


    public static MyPagerFragment newInstance() {

        Bundle args = new Bundle();

        MyPagerFragment fragment = new MyPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_first_my_pager, container, false);
//        EventBusActivityScope.getDefault(_mActivity).register(this);
//        这段代码加上会报错，不知道原因
//        EventBusActivityScope.getDefault(_mActivity).register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        /**
         * user_card和community_card 界面
         */
        user_card = (CardView) view.findViewById(R.id.user_card);
        community_card = (CardView) view.findViewById(R.id.community_card);
        user_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getParentFragment().getParentFragment() != null)
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

        /**
         * ClickableViewPager 界面
         */
        ClickableViewPager mViewPager = (ClickableViewPager) view.findViewById(R.id.viewPager_first);

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
//        mFragmentCardAdapter = new CardFragmentPagerAdapter(_mActivity.getSupportFragmentManager(),
//                dpToPixels(2, getActivity()));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
//        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // your code
                if (getParentFragment().getParentFragment() != null)
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
        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);


    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);
        mFragmentCardShadowTransformer.enableScaling(b);
    }
}
