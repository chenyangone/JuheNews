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
package com.onlyloveyd.juhenews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.onlyloveyd.juhenews.fragment.NewsFragment;

/**
 * 文 件 名: NewsPagerAdapter
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：资讯页面ViewPager的Adapter
 */

public class NewsPagerAdapter extends FragmentPagerAdapter {

  /*  private final String[] titles = {"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
    private final String[] titles_en =
            {"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing",
                    "shishang"};
*/
    private final String[] titles = {"推荐", "最新"};
    private final String[] vtTitles = {"精选热门","定期更新"};
    private final String[] titles_en = {"recommend","hot", "lasted"};


    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NewsFragment.newInstance(titles_en[position]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


  /*  @Override
    public String getVtTitle(int position){
        return vtTitles[position];
    }*/


}
