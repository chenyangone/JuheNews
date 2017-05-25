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

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import com.onlyloveyd.juhenews.R;
import com.onlyloveyd.juhenews.adapter.QueryNewsAdapter;
import com.onlyloveyd.juhenews.gsonbean.querynewsbean.QueryNewsBean;
import com.onlyloveyd.juhenews.utils.OkHttpUtils;

/**
 * 文 件 名: SearchActivity
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：热点搜索
 */
public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_searchResult)
    RecyclerView rvSearchResult;
    @BindView(R.id.toolbar_search)
    Toolbar toolbarSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;

    QueryNewsAdapter queryNewsAdapter;
    ProgressDialog loadingDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);


        loadingDialog = new ProgressDialog(this);
        loadingDialog.setIndeterminate(true);
        loadingDialog.setTitle("提示");
        loadingDialog.setMessage("正在加载...");
        loadingDialog.setCancelable(true);

        toolbarSearch.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbarSearch);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        queryNewsAdapter = new QueryNewsAdapter();
        rvSearchResult.setAdapter(queryNewsAdapter);
        rvSearchResult.setLayoutManager(linearLayoutManager);

    }

    @OnClick({R.id.tv_search})
    public void onClick() {
        loadingDialog.show();
        if (etSearch.getText().toString() != null) {
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                    String response = OkHttpUtils.getCurrentNews(etSearch.getText().toString());
                    emitter.onNext(response);
                    emitter.onComplete();
                }

            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(String response) {
                    Gson gson = new Gson();
                    queryNewsAdapter.setQueryNews(gson.fromJson(response, QueryNewsBean.class));
                }

                @Override
                public void onComplete() {
                    if (loadingDialog.isShowing()) {
                        loadingDialog.cancel();
                    }
                    if (rvSearchResult.getAdapter().getItemCount() == 0) {
                        Toast toast = Toast.makeText(SearchActivity.this, "Sorry, 没有搜索到任何东西", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    //handle exception
                    e.printStackTrace();
                }
            });

        }
    }

    @OnTextChanged(R.id.et_search)
    public void onTextChange(CharSequence text) {
        if (text.toString() == null || text.length() == 0) {
            tvSearch.setTextColor(getResources().getColor(R.color.colorComment));
            tvSearch.setClickable(false);
        } else {
            tvSearch.setTextColor(getResources().getColor(R.color.colorBlack));
            tvSearch.setClickable(true);
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
}
