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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import jzfp.gs.com.juhenews.fragment.NewsFragment;
/**
 * 文 件 名: NewsPagerAdapter
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io/blog
 * 描   述：资讯页面ViewPager的Adapter
 */

public class NewsPagerAdapter extends FragmentPagerAdapter {

    public static final String[] titles = {"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};

    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NewsFragment.newInstance("top");
            case 1:
                return NewsFragment.newInstance("shehui");
            case 2:
                return NewsFragment.newInstance("guonei");
            case 3:
                return NewsFragment.newInstance("guoji");
            case 4:
                return NewsFragment.newInstance("yule");
            case 5:
                return NewsFragment.newInstance("tiyu");
            case 6:
                return NewsFragment.newInstance("junshi");
            case 7:
                return NewsFragment.newInstance("keji");
            case 8:
                return NewsFragment.newInstance("caijing");
            case 9:
                return NewsFragment.newInstance("shishang");
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}
