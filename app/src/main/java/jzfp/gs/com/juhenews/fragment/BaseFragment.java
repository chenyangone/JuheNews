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
package jzfp.gs.com.juhenews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;
import jzfp.gs.com.juhenews.R;
import jzfp.gs.com.juhenews.utils.OkHttpUtils;

/**
 * 文 件 名: BaseFragment
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：Fragment封装父类
 */

@SuppressWarnings("deprecation")
public class BaseFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    public int mPageNum = 1;
    @BindView(R.id.rv_content)
    RecyclerView recyclerView;
    @BindView(R.id.bga_container)
    BGARefreshLayout bgaContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_base, container, false);
        ButterKnife.bind(this, view);
        initBGALayout();
        pullData();
        return view;
    }

    private void initBGALayout() {
        bgaContainer.setDelegate(this);
        BGAStickinessRefreshViewHolder refreshViewHolder = new BGAStickinessRefreshViewHolder(getContext(), true);
        refreshViewHolder.setRotateImage(R.mipmap.bga_refresh_stickiness);
        refreshViewHolder.setStickinessColor(R.color.colorToolbar);
        refreshViewHolder.setLoadingMoreText("加载更多");
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.colorWhite);
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.colorWhite);
        bgaContainer.setRefreshViewHolder(refreshViewHolder);
    }

    public void pullData() {
        if (!OkHttpUtils.isNetworkAvailable(getContext())) {
            if (bgaContainer != null) {
                bgaContainer.endRefreshing();
                Snackbar.make(bgaContainer, "网络链接错误!", Snackbar.LENGTH_SHORT).show();
            }
            return;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPageNum = 1;
        pullData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPageNum++;
        pullData();
        return true;
    }

    /**
     * 处理onCompleted
     */
    public void onDataPullFinished() {
        if (bgaContainer.isLoadingMore()) {
            bgaContainer.endLoadingMore();
        } else {
            bgaContainer.endRefreshing();
        }
        if(recyclerView!= null && recyclerView.getAdapter().getItemCount() == 0) {
            Snackbar.make(bgaContainer, "暂时没有数据", Snackbar.LENGTH_SHORT).show();
        }
    }

    /**
     * 处理onError,给用户提示
     */
    public void onErrorReceived() {
        if (bgaContainer != null) {
            onDataPullFinished();
            Snackbar.make(bgaContainer, "网络请求失败!", Snackbar.LENGTH_SHORT).show();
        }
    }

}
