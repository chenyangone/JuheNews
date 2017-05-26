/**
 * Copyright 2016 yidong
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.onlyloveyd.juhenews.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.Time;

import com.onlyloveyd.juhenews.BuildConfig;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 文 件 名: OkhttpUtils
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：工具类
 */
@SuppressWarnings("deprecation")
public class OkHttpUtils {
    private static final String JOKE_URL =
            "http://japi.juhe.cn/joke/content/text"
                    + ".from?key=facd3f89a62400877ee559778e89bb6c&pagesize=20&page=";
    private static final String FUNNY_URL =
            "http://japi.juhe.cn/joke/img/text"
                    + ".from?key=facd3f89a62400877ee559778e89bb6c&pagesize=20&page=";
    private static final String HISTORY_URL =
            "http://api.juheapi.com/japi/toh?key=e5819f08efaa65bc97a7ef93de55cc46&v=1.0";
    private static final String NEWS_URL =
            "http://op.juhe.cn/onebox/news/query?key=a74ca9043842d6cfe2704f4b765ce2b9&q=";
    private static OkHttpClient okHttpClient;
    private static boolean YLog = false;

    /**
     * 初始化
     */
    public static void create() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        YLog = BuildConfig.YLog;
        return;
    }

    /**
     * 获取新闻头条信息
     */
    public static String getNews(String type) {
        String URL = "http://v.juhe.cn/toutiao/index?type=" + type
                + "&key=53555bf8010e1bf9c42cc0f9fbe8578a";
        return getContentByURL(URL);
    }

    /**
     * 获取笑话信息
     */
    public static String getJokes(int pagenum) {
        if (pagenum < 1) pagenum = 1;
        String url = JOKE_URL + pagenum;
        return getContentByURL(url);
    }


    /**
     * 获取趣图信息
     */
    public static String getFunny(int pagenum) {
        if (pagenum < 1) pagenum = 1;
        String url = FUNNY_URL + pagenum;
        return getContentByURL(url);
    }


    /**
     * 获取历史上的今天的信息
     */
    public static String getHistory() {
        return getContentByURL(getHistoryOnTodayURL());
    }


    /**
     * 拼接历史上今天的请求URL
     */
    private static String getHistoryOnTodayURL() {
        Time time = new Time("GMT+8");
        time.setToNow();
        return (HISTORY_URL + "&month=" + (time.month + 1) + "&day=" + time.monthDay);
    }


    /**
     * 获取实时热点信息，用于搜索功能
     */
    public static String getCurrentNews(String keyword) {
        String URL = NEWS_URL + keyword;
        return getContentByURL(URL);
    }

    /**
     * 通过URL获取对应的内容信息
     */
    private static String getContentByURL(String url) {
        create();
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();//只能调用一次
            if (YLog) {
                System.err.println("URL = " + url + "result = " + result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 判断网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ((ni != null && ni.isConnected()));
    }


}
