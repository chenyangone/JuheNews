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
package com.onlyloveyd.juhenews.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.onlyloveyd.juhenews.R;
import com.onlyloveyd.juhenews.activity.WebActivity;
import com.onlyloveyd.juhenews.gsonbean.newsbean.NewsBean;

import static android.view.View.GONE;

/**
 * 文 件 名: NewsAdapter
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：每一类新闻详情界面Adapter
 */

public class NewsAdapter extends RecyclerView.Adapter {
    Context context;
    private List<NewsBean.ResultBean.DataBean> newsData;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsitem, null, false);
        return (new NewsViewHolder(view));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder && newsData != null) {
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            NewsBean.ResultBean.DataBean data = newsData.get(position);

            newsViewHolder.title.setText(data.getTitle());
            newsViewHolder.author.setText(data.getAuthor_name());
            newsViewHolder.date.setText(data.getDate());

            String pic1path = data.getThumbnail_pic_s();
            if (pic1path != null) {
                AnimationDrawable animationDrawable = (AnimationDrawable) context.getResources().getDrawable(R.drawable.image_loading);
                animationDrawable.start();
                Glide.with(context).load(pic1path).into(newsViewHolder.pic1);
            } else {
                newsViewHolder.pic1.setVisibility(GONE);
            }

            final String url = data.getUrl();
            newsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, WebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("URL", url);
                    intent.putExtra("extra", bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (newsData != null) return newsData.size();
        return 0;
    }

    public void setNews(NewsBean newsBean) {
        this.newsData = newsBean.getResult().getData();
        notifyDataSetChanged();
    }


    class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_news_title)
        public TextView title;
        @BindView(R.id.tv_news_author)
        public TextView author;
        @BindView(R.id.tv_news_date)
        public TextView date;
        @BindView(R.id.iv_news_pic1)
        public ImageView pic1;


        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
