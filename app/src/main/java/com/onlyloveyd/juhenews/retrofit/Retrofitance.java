/**
 * Copyright 2017 yidong
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
package com.onlyloveyd.juhenews.retrofit;

import android.text.format.Time;

import com.onlyloveyd.juhenews.gsonbean.FunnyBean;
import com.onlyloveyd.juhenews.gsonbean.HistoryBean;
import com.onlyloveyd.juhenews.gsonbean.JokeBean;
import com.onlyloveyd.juhenews.gsonbean.NewsBean;
import com.onlyloveyd.juhenews.gsonbean.QueryNewsBean;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 文 件 名: Retrofitance
 * 创 建 人: 易冬
 * 创建日期: 2017/5/26 10:05
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public class Retrofitance {
    public static final String BASE_URL = "http://gank.io/api/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private JuheApi mJuheApi;
    private OkHttpClient mOkHttpClient;

    //构造方法私有
    private Retrofitance() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mOkHttpClient = httpClientBuilder.build();

        retrofit = new Retrofit.Builder().client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mJuheApi = retrofit.create(JuheApi.class);
    }

    //获取单例
    public static Retrofitance getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 根据类型获取新闻信息
     */
    public void getNews(Observer<NewsBean> subscriber, String type) {
        String URL = "http://v.juhe.cn/toutiao/index?type=" + type
                + "&key=53555bf8010e1bf9c42cc0f9fbe8578a";
        commonOp(mJuheApi.getNews(URL),subscriber);
    }

    /**
     * 获取趣图信息
     */
    public void getFunny(Observer<FunnyBean> subscriber, int pagenum) {
        String URL =
                "http://japi.juhe.cn/joke/img/text"
                        + ".from?key=facd3f89a62400877ee559778e89bb6c&pagesize=20&page="
                        + pagenum;
        commonOp(mJuheApi.getFunny(URL), subscriber);
    }

    /**
     * 获取历史上的今天
     */
    public void getHistory(Observer<HistoryBean> subscriber) {
        Calendar now = Calendar.getInstance();
        String URL = "http://api.juheapi.com/japi/toh?key=e5819f08efaa65bc97a7ef93de55cc46&v=1.0"
                + "&month=" + (now.get(Calendar.MONTH) + 1) + "&day=" + now.get(Calendar.DAY_OF_MONTH);
        commonOp(mJuheApi.getTodayInHistory(URL),subscriber);
    }

    /**
     * 获取笑话大全
     */
    public void getJoke(Observer<JokeBean> subscriber, int pagenum) {
        String URL =
                "http://japi.juhe.cn/joke/content/text"
                        + ".from?key=facd3f89a62400877ee559778e89bb6c&pagesize=20&page="
                        + pagenum;
        commonOp(mJuheApi.getJoke(URL), subscriber);
    }


    public void getQueryNews(Observer<QueryNewsBean> subscriber, String keyword) {
        String URL = "http://op.juhe.cn/onebox/news/query?key=a74ca9043842d6cfe2704f4b765ce2b9&q=" + keyword;
        commonOp(mJuheApi.getQueryNews(URL), subscriber);
    }

    private void commonOp(Observable observable, Observer subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Retrofitance INSTANCE = new Retrofitance();
    }
}
