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

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.onlyloveyd.juhenews.R;
import com.onlyloveyd.juhenews.gsonbean.HistoryBean;

/**
 * 文 件 名: HistoryViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/5/26 10:40
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public class HistoryViewHolder extends BaseViewHolder<HistoryBean.ResultBean> {

    public HistoryViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(HistoryBean.ResultBean data) {

        TextView tvTitle = (TextView) getView(R.id.tv_history_title);
        TextView tvDate = (TextView) getView(R.id.tv_history_date);
        TextView tvContent = (TextView) getView(R.id.tv_history_content);
        ImageView imageView = (ImageView) getView(R.id.iv_history_pic);


        tvTitle.setText(data.getTitle());
        tvContent.setText(data.getDes());
        tvDate.setText(data.getYear() + "年" + data.getMonth() + "月" + data.getDay() + "日  "
                + data.getLunar());

        String pic = data.getPic();
        if (pic != null && !pic.isEmpty()) {
            Glide.with(itemView.getContext()).load(pic).into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }
}
