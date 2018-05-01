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
package com.onlyloveyd.juhenews.factory;

import android.view.View;

import com.onlyloveyd.juhenews.gsonbean.EmptyBean;
import com.onlyloveyd.juhenews.gsonbean.FunnyBean;
import com.onlyloveyd.juhenews.gsonbean.HistoryBean;
import com.onlyloveyd.juhenews.gsonbean.JokeBean;
import com.onlyloveyd.juhenews.gsonbean.LoanBean;
import com.onlyloveyd.juhenews.gsonbean.NewsBean;
import com.onlyloveyd.juhenews.gsonbean.QueryNewsBean;
import com.onlyloveyd.juhenews.viewholder.BaseViewHolder;

/**
 * 文 件 名: TypeFactory
 * 创 建 人: 易冬
 * 创建日期: 2017/5/26 10:29
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public interface TypeFactory {
    int type(NewsBean.DataBean newsBean);

    int type(JokeBean.Data jokeBean);

    int type(HistoryBean.ResultBean historyBean);

    int type(FunnyBean.ResultBean.DataBean funnyBean);

    int type(EmptyBean emptyBean);

    int type(QueryNewsBean.ResultBean queryNewsBean);

    int type(LoanBean.ResultBean.DataBean loanBean);

    BaseViewHolder createViewHolder(int type, View itemView);
}
