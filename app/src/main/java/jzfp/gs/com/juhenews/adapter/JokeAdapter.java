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
package jzfp.gs.com.juhenews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jzfp.gs.com.juhenews.R;
import jzfp.gs.com.juhenews.gsonbean.jokebean.JokeBean;

/**
 * 文 件 名: JokeAdapter
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io/blog
 * 描   述：笑话大全界面Adapter
 */

public class JokeAdapter extends RecyclerView.Adapter {
    private List<JokeBean.Data> jokeData;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jokeitem, null, false);
        return (new JokeViewHolder(view));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof JokeViewHolder && jokeData != null) {
            ((JokeViewHolder) holder).content.setText(jokeData.get(position).getContent());
            ((JokeViewHolder) holder).updateTime.setText(jokeData.get(position).getUpdatetime());
        }
    }

    @Override
    public int getItemCount() {
        if (jokeData != null) return jokeData.size();
        return 0;
    }

    public void setJokes(JokeBean jokeBean) {
        this.jokeData = jokeBean.getResult().getData();
        notifyDataSetChanged();
    }

    class JokeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_joke_content)
        public TextView content;
        @BindView(R.id.tv_joke_updatetime)
        public TextView updateTime;

        public JokeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
