package com.ace.qiban.viewinterface.film;

import com.ace.qiban.base.IBaseView;
import com.ace.qiban.bean.filmusbox.FilmUsBox;

/**
 * Created by ace on 16/9/25.
 */
public interface IGetUsBoxView extends IBaseView{

    void getFilmUsBoxSuccess(FilmUsBox filmUsBox);

    /**
     * 网络请求失败
     */
    void getDataFail();
}
