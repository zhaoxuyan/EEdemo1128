package me.yokeyword.sample.demo.ui.fragment.first.child;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.sample.R;
import me.yokeyword.sample.demo.adapter.FirstViewPagerAdapter;
import me.yokeyword.sample.demo.adapter.SecondPagerFragmentAdapter;
import me.yokeyword.sample.demo.helper.DetailTransition;
import me.yokeyword.sample.demo.loader.GlideImageLoader;
import me.yokeyword.sample.demo.ui.fragment.CycleFragment;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class FirstHomeFragment extends SupportFragment {
    private Button select_doctor;
    private TabLayout mTab;
    private ViewPager mViewPager;
    // 图片
    public static List<?> images = new ArrayList<>();
    // 图片title
    public static List<String> titles = new ArrayList<>();

    public static FirstHomeFragment newInstance() {

        Bundle args = new Bundle();

        FirstHomeFragment fragment = new FirstHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_first_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTab = (TabLayout) view.findViewById(R.id.first_tab);
        mViewPager = (ViewPager) view.findViewById(R.id.first_viewPager);

        mTab.addTab(mTab.newTab());

        mViewPager.setAdapter(new FirstViewPagerAdapter(getChildFragmentManager(),
                "我","家人1"));
        mTab.setupWithViewPager(mViewPager);

        // 设置Banner
        // 1. 图片
        // 2. 图片title
        String[] urls = getResources().getStringArray(R.array.url);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        List list1 = Arrays.asList(tips);
        titles = new ArrayList(list1);

        Banner banner = (Banner) view.findViewById(R.id.banner);
        // 设置图片 + 图片title + 数字
        banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImages(images)
                .setBannerTitles(titles)
                .setImageLoader(new GlideImageLoader())
                .start();

    }
    //    private Toolbar mToolbar;
    private RecyclerView mRecy;
//    private SwipeRefreshLayout mRefreshLayout;
//    private FloatingActionButton mFab;
//
    private SecondPagerFragmentAdapter mAdapter;

//
//    private boolean mInAtTop = true;
//    private int mScrollTotal;
//
//    private String[] mTitles = new String[]{
//            "Use imagery to express a distinctive voice and exemplify creative excellence.",
//            "An image that tells a story is infinitely more interesting and informative.",
//            "The most powerful iconic images consist of a few meaningful elements, with minimal distractions.",
//            "Properly contextualized concepts convey your message and brand more effectively.",
//            "Have an iconic point of focus in your imagery. Focus ranges from a single entity to an overarching composition."
//    };
//
    // 原来的图片 未使用
//    private int[] mImgRes = new int[]{
//            R.drawable.bg_first, R.drawable.bg_second, R.drawable.bg_third, R.drawable.bg_fourth, R.drawable.bg_fifth
//    };
//
//    // 图片
//    public static List<?> images = new ArrayList<>();
//    // 图片title
//    public static List<String> titles = new ArrayList<>();
//
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.zhihu_fragment_first_home, container, false);
////        这段代码加上会报错，不知道原因
////        EventBusActivityScope.getDefault(_mActivity).register(this);
//        initView(view);
//        return view;
//    }
//    private void initView(View view) {
//        // 设置Banner
//        // 1. 图片
//        // 2. 图片title
//        String[] urls = getResources().getStringArray(R.array.url);
//        String[] tips = getResources().getStringArray(R.array.title);
//        List list = Arrays.asList(urls);
//        images = new ArrayList(list);
//        List list1 = Arrays.asList(tips);
//        titles = new ArrayList(list1);
//
//        Banner banner = (Banner) view.findViewById(R.id.banner);
//        // 设置图片 + 图片title + 数字
//        banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//        banner.setImages(images)
//                .setBannerTitles(titles)
//                .setImageLoader(new GlideImageLoader())
//                .start();
//
//        // 设置TabLayout
//        mTab = (TabLayout) view.findViewById(R.id.first_tab);
//        mTab.addTab(mTab.newTab());
//        mViewPager = (ViewPager) view.findViewById(R.id.first_viewPager);
//        mViewPager.setAdapter(new FirstViewPagerAdapter(getChildFragmentManager(),
//                "我","家人1"));
//        mTab.setupWithViewPager(mViewPager);
//    }
//
//    private void initView(View view) {
////        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        mRecy = (RecyclerView) view.findViewById(R.id.recy);
//        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
//        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
//
////        if ( getParentFragment().getParentFragment() ==null) {
////            mToolbar.setTitle(R.string.home);
////        }
//        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        mRefreshLayout.setOnRefreshListener(this);
//
//        mAdapter = new SecondPagerFragmentAdapter(_mActivity);
//        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
//        mRecy.setLayoutManager(manager);
//        mRecy.setAdapter(mAdapter);
//
//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
//                if ( getParentFragment().getParentFragment() !=null)
//                    ((SupportFragment) getParentFragment()).start(FirstDetailFragment.newInstance(mAdapter.getItem(position)));
//                else {
//                    FirstDetailFragment fragment = FirstDetailFragment.newInstance(mAdapter.getItem(position));
//                    // 这里是使用SharedElement的用例
//                    // LOLLIPOP(5.0)系统的 SharedElement支持有 系统BUG， 这里判断大于 > LOLLIPOP
//                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//                        setExitTransition(new Fade());
//                        fragment.setEnterTransition(new Fade());
//                        fragment.setSharedElementReturnTransition(new DetailTransition());
//                        fragment.setSharedElementEnterTransition(new DetailTransition());
//
//                        // 25.1.0以下的support包,Material过渡动画只有在进栈时有,返回时没有;
//                        // 25.1.0+的support包，SharedElement正常
//                        extraTransaction()
//                                .addSharedElement(((SecondPagerFragmentAdapter.VH) vh).img, getString(R.string.image_transition))
//                                .addSharedElement(((SecondPagerFragmentAdapter.VH) vh).tvTitle, "tv")
//                                .start(fragment);
//                    } else {
//                        start(fragment);
//                    }
//                }
//            }
//        });
//
//        // Init Datas
//        List<Article> articleList = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            int index = i % 5;
//            Article article = new Article(mTitles[index], mImgRes[index]);
//            articleList.add(article);
//        }
//        mAdapter.setDatas(articleList);
//
//        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                mScrollTotal += dy;
//                if (mScrollTotal <= 0) {
//                    mInAtTop = true;
//                } else {
//                    mInAtTop = false;
//                }
//                if (dy > 5) {
//                    mFab.hide();
//                } else if (dy < -5) {
//                    mFab.show();
//                }
//            }
//        });
//
//        mFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(_mActivity, "Action", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @Override
//    public void onRefresh() {
//        mRefreshLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mRefreshLayout.setRefreshing(false);
//            }
//        }, 2000);
//    }
//
//    private void scrollToTop() {
//        mRecy.smoothScrollToPosition(0);
//    }
//
//    /**
//     * 选择tab事件
//     */
//    @Subscribe
//    public void onTabSelectedEvent(TabSelectedEvent event) {
//        if (event.position != MainActivity.FIRST) return;
//
//        if (mInAtTop) {
//            mRefreshLayout.setRefreshing(true);
//            onRefresh();
//        } else {
//            scrollToTop();
//        }
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        EventBusActivityScope.getDefault(_mActivity).unregister(this);
//    }
}
