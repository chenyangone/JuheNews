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

import android.content.Context;
import android.content.Intent;
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
import jzfp.gs.com.juhenews.R;
import jzfp.gs.com.juhenews.activity.WebActivity;
import jzfp.gs.com.juhenews.gsonbean.funnybean.FunnyBean;

/**
 * 文 件 名: FunnyAdapter
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：趣图界面Adapter
 */

public class FunnyAdapter extends RecyclerView.Adapter {
    private List<FunnyBean.ResultBean.DataBean> funnyData;
    private Context context;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.funnyitem, null, false);
        return (new FunnyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FunnyViewHolder && funnyData != null) {
            FunnyViewHolder funnyViewHolder = (FunnyViewHolder) holder;
            FunnyBean.ResultBean.DataBean resultBean = funnyData.get(position);

            funnyViewHolder.content.setText(resultBean.getContent());
            funnyViewHolder.date.setText(resultBean.getUpdatetime());

            final String pic = resultBean.getUrl();
            if (pic != null && !pic.isEmpty()) {
                funnyViewHolder.gif.setVisibility(View.VISIBLE);
                Glide.with(context).load(pic).placeholder(R.drawable.image_loading).crossFade().into(funnyViewHolder.gif);
            } else {
                funnyViewHolder.gif.setVisibility(View.GONE);
            }

            funnyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, WebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("URL", pic);
                    intent.putExtra("extra", bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (funnyData != null) return funnyData.size();
        return 0;
    }

    /**
     * 添加趣图数据
     *
     * @param funnyBean
     */
    public void addFunnyData(FunnyBean funnyBean) {
        List<FunnyBean.ResultBean.DataBean> dataList = funnyBean.getResult().getData();
        if (funnyData == null) {
            this.funnyData = dataList;
        } else {
            this.funnyData.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    class FunnyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_funny_title)
        public TextView content;
        @BindView(R.id.tv_funny_date)
        public TextView date;
        @BindView(R.id.gv_funny_gif)
        public ImageView gif;


        public FunnyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
