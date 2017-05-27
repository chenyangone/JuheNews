package com.onlyloveyd.juhenews.viewholder;

import static android.view.View.GONE;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.onlyloveyd.juhenews.R;
import com.onlyloveyd.juhenews.activity.WebActivity;
import com.onlyloveyd.juhenews.gsonbean.QueryNewsBean;

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
public class QueryNewsViewHolder extends BaseViewHolder<QueryNewsBean.ResultBean>{
    public QueryNewsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(final QueryNewsBean.ResultBean data) {

        TextView tvTitle = (TextView) getView(R.id.tv_querynews_title);
        TextView tvContent = (TextView) getView(R.id.tv_querynews_content);
        TextView tvAuthor = (TextView) getView(R.id.tv_querynews_author);
        TextView tvDate = (TextView) getView(R.id.tv_querynews_date);
        ImageView imageView = (ImageView) getView(R.id.iv_querynews_pic1);


        tvTitle.setText(data.getTitle());
        tvContent.setText(data.getContent());
        tvAuthor.setText(data.getSrc());
        tvDate.setText(data.getPdate_src());

        String pic1path = data.getImg();
        if (pic1path != null) {
            Glide.with(itemView.getContext()).load(pic1path).placeholder(R.mipmap.empty_data).into(imageView);
        } else {
            imageView.setVisibility(GONE);
        }

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
