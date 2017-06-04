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

import com.onlyloveyd.juhenews.gsonbean.FunnyBean;
import com.onlyloveyd.juhenews.retrofit.Retrofitance;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 文 件 名: FunnyFragment
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：趣图Fragment
 */

public class FunnyFragment extends BaseFragment {
    int pagenum = 1;

    @Override
    public void initBGAData() {
        bgaRefreshLayout.beginRefreshing();
    }

public void getContent(int pagenum) {
    Observer<FunnyBean> observer = new Observer<FunnyBean>() {
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
        public void onNext(FunnyBean funnyBean) {
            if (bgaRefreshLayout.isLoadingMore()) {
            } else {
                mVisitableList.clear();
            }
            if (funnyBean.getResult() == null || funnyBean.getResult().getData() == null
                    || funnyBean.getResult().getData().size() == 0) {
                onDataEmpty();
            } else {
                mVisitableList.addAll(funnyBean.getResult().getData());
            }
            mMultiRecyclerAdapter.setData(mVisitableList);
        }
    };
    Retrofitance.getInstance().getFunny(observer, pagenum);
}

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getContent(1);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        getContent(++pagenum);
        return true;
    }
}
