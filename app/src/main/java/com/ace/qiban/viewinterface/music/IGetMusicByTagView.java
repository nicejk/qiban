package com.ace.qiban.viewinterface.music;

import com.ace.qiban.base.IBaseView;
import com.ace.qiban.bean.music.MusicRoot;

/**
 * Created by ace on 16/9/30.
 */
public interface IGetMusicByTagView extends IBaseView{

    void getMusicByTagSuccess(MusicRoot musicRoot,boolean isLoadMore);
}
