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
import com.onlyloveyd.juhenews.retrofit.Retrofitance;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 文 件 名: NewsViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/5/26 10:39
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public class NewsViewHolder extends BaseViewHolder<NewsBean.DataBean> {
    public NewsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(final NewsBean.DataBean data) {

        TextView tvTitle = (TextView) getView(R.id.tv_news_title);
        TextView tvAuthor = (TextView) getView(R.id.tv_news_author);
        TextView tvDate = (TextView) getView(R.id.tv_news_date);
        ImageView imageView = (ImageView) getView(R.id.iv_news_pic1);


        tvTitle.setText(data.getProduct_name() + "\n"+ data.getProduct_desc());
        tvAuthor.setText(data.getHit_count());
        tvDate.setText(data.getMoney_limit());

        String pic1path = data.getProduct_logo_url();
        if (pic1path != null) {
            pic1path = "http://123.206.66.237" + pic1path;
            Glide.with(itemView.getContext()).load(pic1path).placeholder(R.mipmap.empty_data).into(imageView);
        } else {
            imageView.setVisibility(GONE);
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(itemView.getContext(), WebActivity.class);
                String app_url =  data.getProduct_app_url();
                intent.putExtra("URL", app_url);
                itemView.getContext().startActivity(intent);
                //点击次数上报
                Observer<NewsBean> subscriber = new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                };
                Retrofitance.getInstance().addHitCount(subscriber, data.getId());
            }
        });
    }
}
