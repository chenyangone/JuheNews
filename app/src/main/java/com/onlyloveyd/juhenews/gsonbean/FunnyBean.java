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

import com.google.gson.annotations.SerializedName;
import com.onlyloveyd.juhenews.decorate.Visitable;
import com.onlyloveyd.juhenews.factory.TypeFactory;

/**
 * 文 件 名: FunnyBean
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：GsonFromat自动生成
 */

public class FunnyBean implements Visitable{

    /**
     * title : 家里的小溪流，是有多亲近自然的感觉吧
     * thumburl : http://ww3.sinaimg.cn/large/bc946a85jw1dz86mczufpj.jpg
     * sourceurl : http://down.laifudao.com/images/tupian/20126295294.jpg
     * height : 468
     * width : 700
     * class : 1
     * url : http://www.laifudao.com/tupian/17380.htm
     */

    private String title;
    private String thumburl;
    private String sourceurl;
    private String height;
    private String width;
    @SerializedName("class")
    private String classX;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
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
