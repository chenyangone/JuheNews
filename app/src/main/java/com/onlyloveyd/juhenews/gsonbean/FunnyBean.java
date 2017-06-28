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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onlyloveyd.juhenews.decorate.Visitable;
import com.onlyloveyd.juhenews.factory.TypeFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: FunnyBean
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：GsonFromat自动生成
 */

public class FunnyBean {
    private int error_code;
    private String reason;
    private ResultBean result;

    public static FunnyBean objectFromData(String str) {

        return new Gson().fromJson(str, FunnyBean.class);
    }


    public static List<FunnyBean> arrayFunnyPicBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<FunnyBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }


    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<DataBean> data;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }


        public static List<ResultBean> arrayResultBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ResultBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }


        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Visitable {
            /**
             * content : 谁动了我的冰箱！
             * hashId : DDE51B6C09E1557D6542627755901308
             * unixtime : 1418967626
             * updatetime : 2014-12-19 13:40:26
             * url : http://img.juhe.cn/joke/201412/19/DDE51B6C09E1557D6542627755901308.gif
             */

            private String content;
            private String hashId;
            private int unixtime;
            private String updatetime;
            private String url;

            public static DataBean objectFromData(String str) {

                return new Gson().fromJson(str, DataBean.class);
            }


            public static List<DataBean> arrayDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }


            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
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
    }
}
