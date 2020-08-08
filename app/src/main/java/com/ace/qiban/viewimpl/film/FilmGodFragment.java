package com.ace.qiban.viewimpl.film;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ace.qiban.Presenter.DoubanFilmPresenter;
import com.ace.qiban.R;
import com.ace.qiban.adapter.FilmUsBoxAdapter;
import com.ace.qiban.base.BaseFragment;
import com.ace.qiban.base.EasyRecyclerViewAdapter;
import com.ace.qiban.bean.filmusbox.FilmUsBox;
import com.ace.qiban.utils.ThemeUtils;
import com.ace.qiban.viewimpl.filmdetail.FilmDetailActivity;
import com.ace.qiban.viewinterface.film.IGetUsBoxView;
import com.ace.qiban.widget.SpacesItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import ace.com.douyalibrary.utils.ScreenUtils;

/**
 * 北美榜单
 * Created by ace on 16/9/22.
 */
public class FilmGodFragment extends BaseFragment implements IGetUsBoxView,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.id_swiperefreshlayout)
    SwipeRefreshLayout idSwiperefreshlayout;
    private DoubanFilmPresenter doubanFilmPresenter;
    private FilmUsBoxAdapter filmUsBoxAdapter;

    public static FilmGodFragment newInstance() {

        Bundle args = new Bundle();

        FilmGodFragment fragment = new FilmGodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fim_live, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filmUsBoxAdapter = new FilmUsBoxAdapter(getActivity());
        doubanFilmPresenter = new DoubanFilmPresenter(getActivity());
        doubanFilmPresenter.getUsBox(this);
        idSwiperefreshlayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        idSwiperefreshlayout.setOnRefreshListener(this);

        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        SpacesItemDecoration spacesItemDecoration=new SpacesItemDecoration(ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),10),0);
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(filmUsBoxAdapter);
    }

    @Override
    public boolean hasMultiFragment() {
        return false;
    }

    @Override
    protected String setFragmentName() {
        return null;
    }


    @Override
    public void getFilmUsBoxSuccess(FilmUsBox filmUsBox) {

        filmUsBoxAdapter.setDatas(filmUsBox.getSubjects());
        filmUsBoxAdapter.notifyDataSetChanged();
        filmUsBoxAdapter.setOnItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object data) {
                Intent intent=new Intent(getActivity(),FilmDetailActivity.class);
                intent.putExtra(FilmDetailActivity.EXTRA_ID,filmUsBox.getSubjects().get(position).getSubject().getId());
                startThActivityByIntent(intent);
            }
        });

    }

    @Override
    public void getDataFail() {

    }

    @Override
    public void onRefresh() {
        doubanFilmPresenter.getUsBox(this);

        idSwiperefreshlayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (idSwiperefreshlayout != null) {
                    idSwiperefreshlayout.setRefreshing(false);
                }
            }
        },2000);
    }
}
