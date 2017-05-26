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
package com.onlyloveyd.juhenews.viewholder;

import static android.view.View.GONE;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.onlyloveyd.juhenews.R;
import com.onlyloveyd.juhenews.activity.WebActivity;
import com.onlyloveyd.juhenews.gsonbean.NewsBean;

import butterknife.BindView;

/**
 * 文 件 名: NewsViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/5/26 10:39
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public class NewsViewHolder extends BaseViewHolder<NewsBean.ResultBean.DataBean> {
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
    }

    @Override
    public void bindViewData(final NewsBean.ResultBean.DataBean data) {

        TextView tvTitle = (TextView) getView(R.id.tv_news_title);
        TextView tvAuthor = (TextView) getView(R.id.tv_news_author);
        TextView tvDate = (TextView) getView(R.id.tv_news_date);
        ImageView imageView = (ImageView) getView(R.id.iv_news_pic1);


        tvTitle.setText(data.getTitle());
        tvAuthor.setText(data.getAuthor_name());
        tvDate.setText(data.getDate());

        String pic1path = data.getThumbnail_pic_s();
        if (pic1path != null) {
            Glide.with(itemView.getContext()).load(pic1path).placeholder(R.mipmap.empty_data).into(imageView);
        } else {
            imageView.setVisibility(GONE);
        }

        final String url = data.getUrl();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(itemView.getContext(), WebActivity.class);
                intent.putExtra("URL", data.getUrl());
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
