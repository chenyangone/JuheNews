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

import android.view.View;
import android.widget.TextView;

import com.onlyloveyd.juhenews.R;
import com.onlyloveyd.juhenews.gsonbean.JokeBean;

/**
 * 文 件 名: JokeViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/5/26 10:41
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public class JokeViewHolder extends BaseViewHolder<JokeBean.Data> {
    public JokeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(JokeBean.Data data) {
        TextView tvContent = (TextView) getView(R.id.tv_joke_content);
        TextView tvDate = (TextView) getView(R.id.tv_joke_updatetime);

        tvContent.setText(data.getContent());
        tvDate.setText(data.getUpdatetime());
    }
}
