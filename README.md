![](https://img.shields.io/badge/language-java-orange.svg)![](https://img.shields.io/badge/%E7%89%88%E6%9C%AC-v1.1-yellow.svg) [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
# 聚闻
![icon](/app/src/main/res/mipmap-xxhdpi/ic_launcher.png "")

使用聚合新闻数据而成的新闻资讯客户端

## 下载
[fir.im](https://fir.im/JuKan)

## 数据源
数据来源于：聚合数据，接口每天请求数量受限

## App设计
黑白红，红色采用的是今日头条的红色

## UI设计
1. 黑白红
2. 圆角
3. 材料设计

## 代码设计
1. Retrofit2 + RxJava2 的组合获取网络数据;
2. TabHost + Fragment 作为主界面结构,实现“资讯”，“笑话”，“趣图”，“历史上的今天”四个模块
3. BGARefreshLayout配合RecyclerView和自定义多类型Item结构实现数据的展示;
4. Glide作为图片加载框架；
5. Share功能全部采用系统自带，基本可以满足需求。
6. ButterKnife注解库
7. Gson作为json数据解析库
8. PagerSlidingTabStrip丰富标签页的显示


## 依赖库
* [Glide](https://github.com/bumptech/glide)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [Retrofit](https://github.com/square/retrofit)
* [okhttp3](https://github.com/square/okhttp)
* [Gson](https://github.com/google/gson)
* [ButterKnife](https://github.com/JakeWharton/butterknife)
* [BGARefreshLayout-Android](https://github.com/bingoogolapple/BGARefreshLayout-Android)
* [PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip)

## Author
* [CSDN](http://blog.csdn.net/poorkick)
* [Website](http://www.onlyloveyd.cn/)
* [掘金](https://juejin.im/user/583e860867f356006bbedb90)
* ![个人公众号](/app/src/main/res/mipmap-xxhdpi/qrcode.jpg "")

## 效果图
![实际效果图](/screenshot/Screenshot_1495788428.png "")![实际效果图](/screenshot/Screenshot_1495788432.png "")
![实际效果图](/screenshot/Screenshot_1495788435.png "")![实际效果图](/screenshot/Screenshot_1495788438.png "")