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
import com.ace.qiban.adapter.FilmLiveAdapter;
import com.ace.qiban.base.BaseFragment;
import com.ace.qiban.base.EasyRecyclerViewAdapter;
import com.ace.qiban.bean.filmlive.FilmLive;
import com.ace.qiban.utils.ThemeUtils;
import com.ace.qiban.viewimpl.filmdetail.FilmDetailActivity;
import com.ace.qiban.viewinterface.film.IGetFilmLiveView;
import com.ace.qiban.widget.SpacesItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import ace.com.douyalibrary.utils.ScreenUtils;

/**
 * Created by ace on 16/9/22.
 */
public class FilmLiveFragment extends BaseFragment implements IGetFilmLiveView,SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.id_swiperefreshlayout)
    SwipeRefreshLayout idSwiperefreshlayout;
    private DoubanFilmPresenter doubanFilmPresenter;
    private FilmLiveAdapter filmLiveAdapter;

    public static FilmLiveFragment newInstance() {

        Bundle args = new Bundle();

        FilmLiveFragment fragment = new FilmLiveFragment();
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
        filmLiveAdapter = new FilmLiveAdapter(getActivity());
        doubanFilmPresenter = new DoubanFilmPresenter(getActivity());
        doubanFilmPresenter.getFilmLive(this);
        idSwiperefreshlayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        idSwiperefreshlayout.setOnRefreshListener(this);
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        SpacesItemDecoration spacesItemDecoration=new SpacesItemDecoration(ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),10),0);
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(filmLiveAdapter);
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
    public void getFilmLiveSuccess(FilmLive filmLive) {
        filmLiveAdapter.setDatas(filmLive.getSubjects());
        filmLiveAdapter.notifyDataSetChanged();
        filmLiveAdapter.setOnItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object data) {
                Intent intent=new Intent(getActivity(),FilmDetailActivity.class);
                intent.putExtra(FilmDetailActivity.EXTRA_ID,filmLive.getSubjects().get(position).getId());
                startThActivityByIntent(intent);
            }
        });
    }

    @Override
    public void getDataFail() {

    }

    @Override
    public void onRefresh() {
        doubanFilmPresenter.getFilmLive(this);
        idSwiperefreshlayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (idSwiperefreshlayout != null) {
                    idSwiperefreshlayout.setRefreshing(false);
                }
            }
        },2000);    }
}
