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

import com.onlyloveyd.juhenews.R;
import com.onlyloveyd.juhenews.gsonbean.EmptyBean;
import com.onlyloveyd.juhenews.gsonbean.FunnyBean;
import com.onlyloveyd.juhenews.gsonbean.HistoryBean;
import com.onlyloveyd.juhenews.gsonbean.JokeBean;
import com.onlyloveyd.juhenews.gsonbean.NewsBean;
import com.onlyloveyd.juhenews.gsonbean.QueryNewsBean;
import com.onlyloveyd.juhenews.viewholder.BaseViewHolder;
import com.onlyloveyd.juhenews.viewholder.EmptyViewHolder;
import com.onlyloveyd.juhenews.viewholder.FunnyViewHolder;
import com.onlyloveyd.juhenews.viewholder.HistoryViewHolder;
import com.onlyloveyd.juhenews.viewholder.JokeViewHolder;
import com.onlyloveyd.juhenews.viewholder.NewsViewHolder;

/**
 * 文 件 名: MultiTypeFactory
 * 创 建 人: 易冬
 * 创建日期: 2017/5/26 10:35
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public class MultiTypeFactory implements TypeFactory {
    final int NEWS_ITEM_LAYOUT_ID = R.layout.rv_news_item;
    final int FUNNY_ITEM_LAYOUT_ID = R.layout.rv_funny_item;
    final int HISTORY_ITEM_LAYOUT_ID = R.layout.rv_history_item;
    final int JOKE_ITEM_LAYOUT_ID = R.layout.rv_joke_item;
    final int EMPTY_ITEM_LAYOUT_ID = R.layout.rv_empty_item;

    @Override
    public int type(NewsBean.ResultBean.DataBean newsBean) {
        return NEWS_ITEM_LAYOUT_ID;
    }

    @Override
    public int type(JokeBean.Data jokeBean) {
        return JOKE_ITEM_LAYOUT_ID;
    }

    @Override
    public int type(HistoryBean.ResultBean historyBean) {
        return HISTORY_ITEM_LAYOUT_ID;
    }

    @Override
    public int type(FunnyBean.ResultBean.DataBean funnyBean) {
        return FUNNY_ITEM_LAYOUT_ID;
    }

    @Override
    public int type(EmptyBean emptyBean) {
        return EMPTY_ITEM_LAYOUT_ID;
    }

    @Override
    public int type(QueryNewsBean.ResultBean queryNewsBean) {
        return NEWS_ITEM_LAYOUT_ID;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {
        switch (type) {
            case FUNNY_ITEM_LAYOUT_ID:
                return new FunnyViewHolder(itemView);
            case NEWS_ITEM_LAYOUT_ID:
                return new NewsViewHolder(itemView);
            case JOKE_ITEM_LAYOUT_ID:
                return new JokeViewHolder(itemView);
            case HISTORY_ITEM_LAYOUT_ID:
                return new HistoryViewHolder(itemView);
            case EMPTY_ITEM_LAYOUT_ID:
                return new EmptyViewHolder(itemView);
            default:
                throw new IllegalStateException("incorrect layout type");
        }
    }
}
