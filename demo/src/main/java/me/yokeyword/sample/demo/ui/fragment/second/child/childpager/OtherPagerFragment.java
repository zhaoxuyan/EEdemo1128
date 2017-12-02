package me.yokeyword.sample.demo.ui.fragment.second.child.childpager;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.sample.R;
import me.yokeyword.sample.demo.MainActivity;
import me.yokeyword.sample.demo.adapter.SecondPagerFragmentAdapter;
import me.yokeyword.sample.demo.entity.Article;
import me.yokeyword.sample.demo.event.TabSelectedEvent;
import me.yokeyword.sample.demo.helper.DetailTransition;
import me.yokeyword.sample.demo.listener.OnItemClickListener;
import me.yokeyword.sample.demo.ui.fragment.second.child.FirstPagerDetailFragment;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class OtherPagerFragment extends SupportFragment implements SwipeRefreshLayout.OnRefreshListener {
    private Toolbar mToolbar;
    private RecyclerView mRecy;
    private SwipeRefreshLayout mRefreshLayout;
    private FloatingActionButton mFab;

    private SecondPagerFragmentAdapter mAdapter;

    private boolean mInAtTop = true;
    private int mScrollTotal;

    private String[] mTitles = new String[]{
            "Use imagery to express a distinctive voice and exemplify creative excellence.",
            "An image that tells a story is infinitely more interesting and informative.",
            "The most powerful iconic images consist of a few meaningful elements, with minimal distractions.",
            "Properly contextualized concepts convey your message and brand more effectively.",
            "Have an iconic point of focus in your imagery. Focus ranges from a single entity to an overarching composition."
    };

    private int[] mImgRes = new int[]{
            R.drawable.other_pager_1, R.drawable.other_pager_2, R.drawable.other_pager_3, R.drawable.other_pager_4, R.drawable.other_pager_5
    };


    public static OtherPagerFragment newInstance() {

        Bundle args = new Bundle();

        OtherPagerFragment fragment = new OtherPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_fragment_second_pager_first, container, false);
        EventBusActivityScope.getDefault(_mActivity).register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);

        if ( getParentFragment().getParentFragment() ==null) {
            mToolbar.setTitle(R.string.home);
        }
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mAdapter = new SecondPagerFragmentAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                if ( getParentFragment().getParentFragment() !=null)
                    ((SupportFragment) getParentFragment()).start(FirstPagerDetailFragment.newInstance(mAdapter.getItem(position)));
                else {
                    FirstPagerDetailFragment fragment = FirstPagerDetailFragment.newInstance(mAdapter.getItem(position));
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
                                .addSharedElement(((SecondPagerFragmentAdapter.VH) vh).img, getString(R.string.image_transition))
                                .addSharedElement(((SecondPagerFragmentAdapter.VH) vh).tvTitle, "tv")
                                .start(fragment);
                    } else {
                        start(fragment);
                    }
                }
            }
        });

        // Init Datas
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int index = i % 5;
            Article article = new Article(mTitles[index], mImgRes[index]);
            articleList.add(article);
        }
        mAdapter.setDatas(articleList);

        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollTotal += dy;
                if (mScrollTotal <= 0) {
                    mInAtTop = true;
                } else {
                    mInAtTop = false;
                }
                if (dy > 5) {
                    mFab.hide();
                } else if (dy < -5) {
                    mFab.show();
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_mActivity, "Action", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    private void scrollToTop() {
        mRecy.smoothScrollToPosition(0);
    }

    /**
     * 选择tab事件
     */
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != MainActivity.FIRST) return;

        if (mInAtTop) {
            mRefreshLayout.setRefreshing(true);
            onRefresh();
        } else {
            scrollToTop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusActivityScope.getDefault(_mActivity).unregister(this);
    }
}
