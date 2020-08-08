package com.ace.qiban.viewinterface.book;

import com.ace.qiban.bean.book.Books;

/**
 * Created by ace on 16/10/2.
 */
public interface IGetBookDetailView {

    void getBookSuccess(Books books);
    void getBookFail();
}
