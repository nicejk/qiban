package com.ace.qiban.viewinterface.book;

import com.ace.qiban.base.IBaseView;
import com.ace.qiban.bean.book.BookRoot;

/**
 * Created by ace on 16/9/29.
 */
public interface IGetBookView extends IBaseView {
    void getBookSuccess(BookRoot bookRoot,boolean isLoadMore);
    void getBookFail();
}
