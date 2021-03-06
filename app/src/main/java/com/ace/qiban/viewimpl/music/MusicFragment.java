package com.ace.qiban.viewimpl.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ace.qiban.R;
import com.ace.qiban.adapter.MusicViewpagerAdapter;
import com.ace.qiban.api.MusicApiUtils;
import com.ace.qiban.base.BaseFragment;
import com.ace.qiban.utils.ThemeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ace on 16/8/13.
 */
public class MusicFragment extends BaseFragment implements ViewPager.OnPageChangeListener{


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorlayout;
    // TabLayout中的tab标题
    private String[] mTitles;

    private MusicViewpagerAdapter mViewPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_music,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static MusicFragment newInstance() {

        Bundle args = new Bundle();

        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();

    }

    private void initViews(){

        mTitles = MusicApiUtils.Music_Titles;

        // 初始化ViewPager的适配器，并设置给它
        mViewPagerAdapter = new MusicViewpagerAdapter(getChildFragmentManager(), mTitles);
        viewpager.setAdapter(mViewPagerAdapter);
        // 设置ViewPager最大缓存的页面个数
        viewpager.setOffscreenPageLimit(4);
        // 给ViewPager添加页面动态监听器（为了让Toolbar中的Title可以变化相应的Tab的标题）
        viewpager.addOnPageChangeListener(this);

        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setSelectedTabIndicatorColor(ThemeUtils.getThemeColor());
        tablayout.setTabTextColors(getResources().getColor(R.color.text_gray_6),ThemeUtils.getThemeColor());
        // 将TabLayout和ViewPager进行关联，让两者联动起来
        tablayout.setupWithViewPager(viewpager);
        // 设置Tablayout的Tab显示ViewPager的适配器中的getPageTitle函数获取到的标题
        tablayout.setTabsFromPagerAdapter(mViewPagerAdapter);


    }

    @Override
    public boolean hasMultiFragment() {
        return false;
    }

    @Override
    protected String setFragmentName() {
        return "动态";
    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
