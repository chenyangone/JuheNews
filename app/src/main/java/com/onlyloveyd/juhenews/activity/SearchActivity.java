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
package com.onlyloveyd.juhenews.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.onlyloveyd.juhenews.R;
import com.onlyloveyd.juhenews.adapter.MultiRecyclerAdapter;
import com.onlyloveyd.juhenews.adapter.QueryNewsAdapter;
import com.onlyloveyd.juhenews.decorate.Visitable;
import com.onlyloveyd.juhenews.gsonbean.EmptyBean;
import com.onlyloveyd.juhenews.gsonbean.QueryNewsBean;
import com.onlyloveyd.juhenews.retrofit.Retrofitance;
import com.onlyloveyd.juhenews.utils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: SearchActivity
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：热点搜索
 */
public class SearchActivity extends AppCompatActivity implements
        BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.toolbar_search)
    Toolbar mToolbarSearch;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.search_bga_refresh)
    BGARefreshLayout mSearchBgaRefresh;
    @BindView(R.id.activity_search)
    LinearLayout mActivitySearch;

    MultiRecyclerAdapter mMultiRecyclerAdapter;
    List<Visitable> mVisitableList = new ArrayList<>();
    private String keyword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mToolbarSearch.setNavigationIcon(R.drawable.back);
        setSupportActionBar(mToolbarSearch);
        initBGALayout();
        initRvContent();
    }

    private void initBGALayout() {
        // 为BGARefreshLayout 设置代理
        mSearchBgaRefresh.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder =
                new BGANormalRefreshViewHolder(this, true);
        refreshViewHolder.setLoadingMoreText("加载更多");
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.white);
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.white);
        mSearchBgaRefresh.setRefreshViewHolder(refreshViewHolder);
    }

    private void initRvContent() {
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        mMultiRecyclerAdapter = new MultiRecyclerAdapter(null);
        mRvContent.setLayoutManager(llm);
        mRvContent.setAdapter(mMultiRecyclerAdapter);
    }

    @OnClick({R.id.tv_search})
    public void onClick() {
        if (mEtSearch.getText().toString() != null) {
            queryNews(mEtSearch.getText().toString());
        }
    }


    private void queryNews(String keyword) {
        Observer<QueryNewsBean> subscriber = new Observer<QueryNewsBean>() {
            @Override
            public void onComplete() {
                if (mSearchBgaRefresh.isLoadingMore()) {
                    mSearchBgaRefresh.endLoadingMore();
                } else {
                    mSearchBgaRefresh.endRefreshing();
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (mSearchBgaRefresh.isLoadingMore()) {
                    mSearchBgaRefresh.endLoadingMore();
                } else {
                    mSearchBgaRefresh.endRefreshing();
                }
                mVisitableList.clear();
                EmptyBean emptyBean = new EmptyBean();
                emptyBean.setMessage("网络请求错误");
                mVisitableList.add(0, emptyBean);
                mMultiRecyclerAdapter.setData(mVisitableList);
            }

            @Override
            public void onSubscribe(@android.support.annotation.NonNull Disposable d) {

            }

            @Override
        public void onNext(QueryNewsBean queryNewsBean) {
                if (mSearchBgaRefresh.isLoadingMore()) {
                } else {
                    mVisitableList.clear();
                }
                if (queryNewsBean.getResult() == null || queryNewsBean.getResult().size() == 0) {
                    EmptyBean emptyBean = new EmptyBean();
                    emptyBean.setMessage("没有搜到什么东西，怎么办？");
                    mVisitableList.add(0, emptyBean);
                } else {
                    mVisitableList.addAll(queryNewsBean.getResult());
                }
                mMultiRecyclerAdapter.setData(mVisitableList);
            }
        };
        Retrofitance.getInstance().getQueryNews(subscriber, keyword);
    }

    @OnTextChanged(R.id.et_search)
    public void onTextChange(CharSequence text) {
        keyword = text.toString();
        if (text.toString().equals("") || text.length() == 0) {
            mTvSearch.setTextColor(getResources().getColor(R.color.colorToolbar));
            mTvSearch.setClickable(false);
        } else {
            mTvSearch.setTextColor(getResources().getColor(R.color.colorWhite));
            mTvSearch.setClickable(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        queryNews(keyword);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
