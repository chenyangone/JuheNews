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
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import com.onlyloveyd.juhenews.adapter.FunnyAdapter;
import com.onlyloveyd.juhenews.gsonbean.funnybean.FunnyBean;
import com.onlyloveyd.juhenews.utils.OkHttpUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 文 件 名: FunnyFragment
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：趣图Fragment
 */

@SuppressWarnings("deprecation")
public class FunnyFragment extends BaseFragment {
    private FunnyAdapter funnyAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        funnyAdapter = new FunnyAdapter();
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(funnyAdapter);
        return view;
    }

    @Override
    public void pullData() {
        super.pullData();

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                String response = OkHttpUtils.getFunny(mPageNum);
                emitter.onNext(response);
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(String response) {
                        Gson gson = new Gson();
                        FunnyBean funnyBean = gson.fromJson(response, FunnyBean.class);
                        funnyAdapter.addFunnyData(funnyBean);
                    }

                    @Override
                    public void onComplete() {
                        onDataPullFinished();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onErrorReceived();
                        e.printStackTrace();
                    }
                });

    }
}
