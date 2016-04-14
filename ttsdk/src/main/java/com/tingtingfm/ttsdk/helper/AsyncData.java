package com.tingtingfm.ttsdk.helper;

import com.tingtingfm.ttsdk.Api;
import com.tingtingfm.ttsdk.callback.ListAlbumCallBack;
import com.tingtingfm.ttsdk.callback.ListCategoryCallBack;
import com.tingtingfm.ttsdk.callback.ListSelectFmCallBack;
import com.tingtingfm.ttsdk.callback.ListVodCallBack;
import com.tingtingfm.ttsdk.callback.SearchAlbumCallBack;
import com.tingtingfm.ttsdk.callback.SearchVodCallBack;
import com.tingtingfm.ttsdk.net.DefaultThreadPool;
import com.tingtingfm.ttsdk.net.HttpRequest;
import com.tingtingfm.ttsdk.net.RequestCallback;
import com.tingtingfm.ttsdk.net.RequestEntity;

/**
 * 异步请求
 * Created by lqsir on 2016/3/28.
 */
public class AsyncData {

    /**
     * 获取广播一级分类列表
     *
     * @param tag
     * @param callBack
     */
    public static void showFmFirstCategory(String tag, ListCategoryCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.FM_CATEGORY_LIST);
        showCategoryList(entity, callBack);
    }

    /**
     * 获取广播二级分类列表
     * @param tag
     * @param type
     * @param callBack
     */
    public static void showFmSecondCategory(String tag, String type, ListCategoryCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.FM_SUB_CATEGORY_LIST);
        entity.addParams("type", type);
        showCategoryList(entity, callBack);
    }

    /**
     * 获取广播某个分类下频率列表
     * @param tag
     * @param type
     * @param page
     * @param callBack
     */
    public static void showFmListForType(String tag, String type, int page, ListSelectFmCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.FM_LIST_DETAIL);

    }

    /**
     * 获取音乐分类列表
     * @param tag
     * @param callBack
     */
    public static void showMusicFmCategory(String tag, ListCategoryCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.MUSIC_CATEGORY_LIST);
        showCategoryList(entity, callBack);
    }


    /**
     * 获取音乐某个分类下频率列表
     * @param tag
     * @param type
     * @param page
     * @param callBack
     */
    public static void showMusicFmListForType(String tag, String type, int page, ListSelectFmCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.MUSIC_FM_LIST_DETAIL);
    }

    /**
     * 获取点播一级分类列表
     *
     * @param tag
     * @param callBack
     */
    public static void showVodFirstCategory(String tag, ListCategoryCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.VOD_CATEGORY_LIST);
        showCategoryList(entity, callBack);
    }


    /**
     * 获取点播二级分类列表
     * @param tag
     * @param type
     * @param callBack
     */
    public static void showVodSecondCategory(String tag, String type, ListCategoryCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.VOD_SUB_CATEGORY_LIST);
        entity.addParams("type", type);
        showCategoryList(entity, callBack);
    }

    /**
     * 获取点播二级分类下的专辑列表
     * @param tag
     * @param type
     * @param callBack
     */
    public static void showAlbumListForType(String tag, String type, ListAlbumCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.VOD_ALBUM_LIST);

    }

    /**
     * 获取点播专辑下音频列表
     * @param tag
     * @param type
     * @param callBack
     */
    public static void showVodListForType(String tag, String type, ListAlbumCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.VOD_ALBUM_AUDIO_LIST);

    }

    /**
     * 搜专辑
     * @param tag
     * @param keyword
     * @param page
     * @param callBack
     */
    public static void showSearchAlbum(String tag, String keyword, int page, SearchAlbumCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.SEARCH_ALBUM_LIST);

    }

    /**
     * 搜音频
     * @param tag
     * @param keyword
     * @param page
     * @param callBack
     */
    public static void showSearchVod(String tag, String keyword, int page, SearchVodCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.SEARCH_VOD_LIST);

    }

    /**
     * 搜频率
     * @param tag
     * @param keyword
     * @param page
     * @param callBack
     */
    public static void showSearchFm(String tag, String keyword, int page, SearchAlbumCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.SEARCH_FM_LIST);

    }

    /**
     * 获取精选电台列表
     * @param tag
     * @param page
     * @param callBack
     */
    public static void showSelectFm(String tag, int page, ListSelectFmCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.SELECTED_FM_LIST);
        entity.addParams("page", page+"");
        showCategoryList(entity, callBack);
    }

    /**
     * 获取精选电台下音频列表
     * @param tag
     * @param type
     * @param page
     * @param callBack
     */
    public static void showSelectFmVod(String tag, String type, int page, ListVodCallBack callBack) {
        RequestEntity entity = new RequestEntity(tag, Api.SELECTED_FM_LIST_DETAIL);

    }

    private static void showCategoryList(RequestEntity requestEntity, RequestCallback callBack) {
        HttpRequest request = ConfigurationManager.getInstance().getManager().createRequest(
                requestEntity.getUrl(), requestEntity.getParameters(), callBack);
        DefaultThreadPool.getInstance().execute(request);
    }


}
