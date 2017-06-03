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

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.onlyloveyd.juhenews.R;
import com.onlyloveyd.juhenews.activity.WebActivity;
import com.onlyloveyd.juhenews.gsonbean.FunnyBean;

/**
 * 文 件 名: FunnyViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/5/26 10:41
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public class FunnyViewHolder extends BaseViewHolder<FunnyBean> {
    public FunnyViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(final FunnyBean data) {
        ImageView imageView = (ImageView) getView(R.id.iv_funny_gif);
        TextView tvTitle = (TextView) getView(R.id.tv_funny_title);

        tvTitle.setText(data.getTitle());
        //tvDate.setText(data.getUpdatetime());

        final String pic = data.getThumburl();
        if (pic != null && !pic.isEmpty()) {

            Glide.with(itemView.getContext()).load(pic).placeholder(R.mipmap.empty_data).into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
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
