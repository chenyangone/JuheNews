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
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onlyloveyd.juhenews.R;
import com.onlyloveyd.juhenews.adapter.MultiRecyclerAdapter;
import com.onlyloveyd.juhenews.decorate.Visitable;
import com.onlyloveyd.juhenews.gsonbean.EmptyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 文 件 名: BaseFragment
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：Fragment封装父类
 */

public class BaseFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.bga_container)
    BGARefreshLayout bgaRefreshLayout;

    MultiRecyclerAdapter mMultiRecyclerAdapter;
    List<Visitable> mVisitableList = new ArrayList<>();

    LinearLayoutManager llm;
    String arg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, null, false);
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        if (args != null) {
            arg = args.getString("ARG");
        }

        initBGALayout();
        initRvContent();
        initBGAData();
        return view;
    }

private void initBGALayout() {
    // 为BGARefreshLayout 设置代理
    bgaRefreshLayout.setDelegate(this);
    // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能

    BGANormalRefreshViewHolder refreshViewHolder =
            new BGANormalRefreshViewHolder(getContext(), true);
    refreshViewHolder.setLoadingMoreText("加载更多");
    refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.white);
    refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.white);
    bgaRefreshLayout.setRefreshViewHolder(refreshViewHolder);
}

    private void initRvContent() {
        llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mMultiRecyclerAdapter = new MultiRecyclerAdapter(null);
        rvContent.setLayoutManager(llm);
        rvContent.setAdapter(mMultiRecyclerAdapter);
    }

    public void initBGAData() {
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    /**
     * 处理网络请求错误
     */
    public void onNetworkError() {
        mVisitableList.clear();
        EmptyBean emptyBean = new EmptyBean();
        emptyBean.setMessage("网络请求错误");
        mVisitableList.add(0, emptyBean);
        mMultiRecyclerAdapter.setData(mVisitableList);
    }

    /**
     * 处理请求数据为空
     */
    public void onDataEmpty() {
        EmptyBean emptyBean = new EmptyBean();
        emptyBean.setMessage("这里空空的，什么都没有呢");
        mVisitableList.add(0, emptyBean);
    }

    /**
     * 停止刷新或者加载更多
     */
    public void endLoading() {
        if (bgaRefreshLayout.isLoadingMore()) {
            bgaRefreshLayout.endLoadingMore();
        } else {
            bgaRefreshLayout.endRefreshing();
        }
    }
}
