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
 * 文 件 名: LoanBean
 * 创 建 人: 易冬
 * 创建日期: 2016/12/27 08:44
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: http://onlyloveyd.github.io
 * 描   述：GsonFromat自动生成
 */

public class LoanBean {
    private String msg;
    private ResultBean data;
    private int code;

    public static LoanBean objectFromData(String str) {

        return new Gson().fromJson(str, LoanBean.class);
    }


    public static List<LoanBean> arrayLoanBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<LoanBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getData() {
        return data;
    }

    public void setData(ResultBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
            private String id;
            private String product_name;
            private String product_desc;
            private String product_app_url;
            private String product_logo_url;
            private String money_limit;
            private String interest_rate;
            private String end_time;
            private String create_time;
            private String update_time;
            private String hit_count;

            public static DataBean objectFromData(String str) {

                return new Gson().fromJson(str, DataBean.class);
            }


            public static List<DataBean> arrayDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getProduct_desc() {
                return product_desc;
            }

            public void setProduct_desc(String product_desc) {
                this.product_desc = product_desc;
            }

            public String getProduct_app_url() {
                return product_app_url;
            }

            public void setProduct_app_url(String product_app_url) {
                this.product_app_url = product_app_url;
            }

            public String getProduct_logo_url() {
                return product_logo_url;
            }

            public void setProduct_logo_url(String product_logo_url) {
                this.product_logo_url = product_logo_url;
            }

            public String getMoney_limit() {
                return money_limit;
            }

            public void setMoney_limit(String money_limit) {
                this.money_limit = money_limit;
            }

            public String getInterest_rate() {
                return interest_rate;
            }

            public void setInterest_rate(String interest_rate) {
                this.interest_rate = interest_rate;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getHit_count() {
                return hit_count;
            }

            public void setHit_count(String hit_count) {
                this.hit_count = hit_count;
            }

            @Override
            public int type(TypeFactory typeFactory) {
                return typeFactory.type(this);
            }
        }
    }
}
