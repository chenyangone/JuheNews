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
package com.onlyloveyd.juhenews.gsonbean;

import com.onlyloveyd.juhenews.decorate.Visitable;
import com.onlyloveyd.juhenews.factory.TypeFactory;

/**
 * 文 件 名: JokeBean
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：GsonFromat自动生成
 */
public class JokeBean implements Visitable{


    /**
     * title : 你要吓死我
     * content : 　　老公在家玩游戏，手机响了：老公，快点送钱来！我在人民医院门口等你！<br/><br
     * />　　老公：老婆，你不是和闺蜜逛街的吗？怎么到人民医院去了，出了什么事情？<br/><br/>　　老婆：别废话了，赶紧点！<br/><br
     * />　　老公一听：人命关天，赶紧点吧。说着关机，出门。<br/><br/>　　到了医院大门口，看到老婆焦急的在看着，立马走过去：怎么回事？你闺蜜呢？是不是她出了什么问题？<br
     * /><br/>　　老婆：在那看着呢，钱呢？说着一边拿过钱，一边朝着医院大门左侧走去。<br/><br/>　　老公：老婆，她在那看着病，你怎么不往医院里面走？<br/><br
     * />　　老婆：看什么病？她在这边的服装店里看着那件衣服呢，最后一件了！<br/><br/>　　老公：尼玛，你要吓死我……
     * poster :
     * url : http://www.laifudao.com/wangwen/120596.htm
     */

    private String title;
    private String content;
    private String poster;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}