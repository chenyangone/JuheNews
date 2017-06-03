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

import com.onlyloveyd.juhenews.gsonbean.FunnyBean;
import com.onlyloveyd.juhenews.gsonbean.HistoryBean;
import com.onlyloveyd.juhenews.gsonbean.JokeBean;
import com.onlyloveyd.juhenews.gsonbean.NewsBean;
import com.onlyloveyd.juhenews.gsonbean.QueryNewsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 文 件 名: JuheApi
 * 创 建 人: 易冬
 * 创建日期: 2017/5/26 10:09
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public interface JuheApi {
    @GET
    Observable<NewsBean> getNews(@Url String url);

    @GET
    Observable<List<FunnyBean>> getFunny(@Url String url);

    @GET
    Observable<List<JokeBean>> getJoke(@Url String url);

    @GET
    Observable<HistoryBean> getTodayInHistory(@Url String url);

    @GET
    Observable<QueryNewsBean> getQueryNews(@Url String url);
}
