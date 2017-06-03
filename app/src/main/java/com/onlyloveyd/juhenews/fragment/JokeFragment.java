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

import com.onlyloveyd.juhenews.gsonbean.JokeBean;
import com.onlyloveyd.juhenews.retrofit.Retrofitance;

import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 文 件 名: JokeFragment
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：笑话大全Fragment
 */

@SuppressWarnings("deprecation")
public class JokeFragment extends BaseFragment {

    @Override
    public void initBGAData() {
        bgaRefreshLayout.beginRefreshing();
    }

    public void getContent() {
        Observer<List<JokeBean>> observer = new Observer<List<JokeBean>>() {
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
            public void onNext(List<JokeBean> jokeBeanList) {
                if (bgaRefreshLayout.isLoadingMore()) {
                } else {
                    mVisitableList.clear();
                }
                if (jokeBeanList==null || jokeBeanList.size()==0) {
                    onDataEmpty();
                } else {
                    mVisitableList.addAll(jokeBeanList);
                }
                mMultiRecyclerAdapter.setData(mVisitableList);
            }
        };
        Retrofitance.getInstance().getJoke(observer);
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
