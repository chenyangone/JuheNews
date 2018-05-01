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
package com.onlyloveyd.juhenews.fragment;

import android.os.Bundle;

import com.onlyloveyd.juhenews.gsonbean.NewsBean;
import com.onlyloveyd.juhenews.retrofit.Retrofitance;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 文 件 名: NewsFragment
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：新闻Fragment
 */

public class NewsFragment extends BaseFragment {
    public static NewsFragment newInstance(String type) {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        args.putString("ARG", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initBGAData() {
        bgaRefreshLayout.beginRefreshing();
    }

    public void getContent() {
        Observer<NewsBean> observer = new Observer<NewsBean>() {
            @Override
            public void onComplete() {
                endLoading();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                endLoading();
                onNetworkError();
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewsBean newsBean) {
                if (bgaRefreshLayout.isLoadingMore()) {
                } else {
                    mVisitableList.clear();
                }
                if (newsBean == null || newsBean.getData() == null
                        || newsBean.getData().size() == 0) {
                    onDataEmpty();
                } else {
                    mVisitableList.addAll(newsBean.getData());
                }
                mMultiRecyclerAdapter.setData(mVisitableList);
            }
        };
        Retrofitance.getInstance().getNews(observer, arg);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getContent();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
