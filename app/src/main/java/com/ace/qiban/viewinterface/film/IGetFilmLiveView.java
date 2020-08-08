package com.ace.qiban.viewinterface.film;

import com.ace.qiban.base.IBaseView;
import com.ace.qiban.bean.filmlive.FilmLive;

/**
 * Created by ace on 16/9/23.
 */
public interface IGetFilmLiveView extends IBaseView {

    void getFilmLiveSuccess(FilmLive filmLive);

    /**
     * 网络请求失败
     */
    void getDataFail();
}
