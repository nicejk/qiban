package com.ace.qiban.viewinterface.film;

import com.ace.qiban.base.IBaseView;
import com.ace.qiban.bean.top250.Root;

/**
 * Created by ace on 16/9/21.
 */
public interface IgetTop250View extends IBaseView{
    void getTop250Success(Root root,boolean isLoadMore);

    /**
     * 网络请求失败
     */
    void getDataFail();

}
