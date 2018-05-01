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
package com.onlyloveyd.juhenews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.onlyloveyd.juhenews.R;
import com.onlyloveyd.juhenews.adapter.NewsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: ZixunFragment
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：资讯内容Fragment
 */

public class ZixunFragment extends Fragment {

    @BindView(R.id.psts_title)
    PagerSlidingTabStrip pagerSlidingTabStrip = null;
    @BindView(R.id.vp_news)
    ViewPager viewPager = null;

    /*@BindView(R.id.tv_zixun_title)
    TextView tvTitle = null;*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zixun, container, false);
        ButterKnife.bind(this, view);
        //tvTitle.setText("精选优质");
        viewPager.setAdapter(new NewsPagerAdapter(getFragmentManager()));
        viewPager.setOffscreenPageLimit(4);
        pagerSlidingTabStrip.setViewPager(viewPager);
        return view;
    }
}
