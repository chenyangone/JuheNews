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
package jzfp.gs.com.juhenews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import jzfp.gs.com.juhenews.adapter.NewsAdapter;
import jzfp.gs.com.juhenews.gsonbean.newsbean.NewsBean;
import jzfp.gs.com.juhenews.utils.OkHttpUtils;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 文 件 名: NewsFragment
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：新闻Fragment
 */

public class NewsFragment extends BaseFragment {
    private NewsAdapter newsAdapter;
    private String type = null;

    /*
     * new instance 方法 获取JokeFragment
     */
    public static NewsFragment newInstance(String type) {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        args.putString("TYPE", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            type = args.getString("TYPE");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        newsAdapter = new NewsAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsAdapter);
        return view;
    }

    @Override
    public void pullData() {
        super.pullData();
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String response = OkHttpUtils.getNews(type);
                subscriber.onNext(response);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onNext(String response) {
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(response, NewsBean.class);
                newsAdapter.setNews(newsBean);
            }

            @Override
            public void onCompleted() {
                onDataPullFinished();
            }

            @Override
            public void onError(Throwable e) {
                onErrorReceived();
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
