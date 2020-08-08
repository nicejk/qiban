package com.ace.qiban.viewinterface.film;

import com.ace.qiban.base.IBaseView;
import com.ace.qiban.bean.filmdetail.FilmDetail;

/**
 * Created by ace on 16/9/25.
 */
public interface IGetFilmDetail extends IBaseView{

    void getFilmDetailSuccess(FilmDetail filmDetail);

    /**
     * 网络请求失败
     */
    void getDataFail();

}
