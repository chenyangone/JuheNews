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
package jzfp.gs.com.juhenews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jzfp.gs.com.juhenews.R;

/**
 * 文 件 名: TabHostActivity
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io/blog
 * 描   述：主页面
 */
public class TabHostActivity extends AppCompatActivity {

    @BindView(R.id.tabhost)
    TabHost tabHost = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextAppearance(this, R.style.ToolBarTextAppearance);
        setSupportActionBar(toolbar);

        tabHost.setup();//初始化TabHost
        /*添加tab*/
        for (int i = 0; i < 4; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.tab_widget_indicator, null, false);
            TextView textView = (TextView) view.findViewById(R.id.tv_indicator);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_indicator);

            switch (i) {
                case 0:
                    textView.setText("资讯");
                    imageView.setImageResource(R.drawable.news);
                    tabHost.addTab(tabHost.newTabSpec("1").setIndicator(view).setContent(R.id.frag_news));
                    break;
                case 1:
                    textView.setText("笑话");
                    imageView.setImageResource(R.drawable.joke);
                    tabHost.addTab(tabHost.newTabSpec("2").setIndicator(view).setContent(R.id.frag_joke));
                    break;
                case 2:
                    textView.setText("趣图");
                    imageView.setImageResource(R.drawable.funny);
                    tabHost.addTab(tabHost.newTabSpec("3").setIndicator(view).setContent(R.id.frag_funny));
                    break;
                case 3:
                    textView.setText("历史");
                    imageView.setImageResource(R.drawable.history);
                    tabHost.addTab(tabHost.newTabSpec("4").setIndicator(view).setContent(R.id.frag_history));
                    break;
                default:
                    break;
            }

        }
        /*设置标签切换监听器*/
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < 4; i++) {//颜色全部重置
                    ((TextView) tabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tv_indicator)).setTextColor(getResources().getColor(R.color.colorBlack));
                }
                if (tabHost.getCurrentTabTag().equals(tabId)) {
                    ((TextView) tabHost.getCurrentTabView().findViewById(R.id.tv_indicator)).setTextColor(getResources().getColor(R.color.colorBottomSelected));
                }//选中的那个Tab文字颜色修改
            }
        });
        ((TextView) tabHost.getCurrentTabView().findViewById(R.id.tv_indicator)).setTextColor(getResources().getColor(R.color.colorBottomSelected));//第一次进入的时候讲选中的Tab修改文字颜色

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.search: {
                Intent intent = new Intent();
                intent.setClass(this, SearchActivity.class);
                this.startActivity(intent);
            }
            break;
            default:
                break;
        }
        return true;
    }

}
