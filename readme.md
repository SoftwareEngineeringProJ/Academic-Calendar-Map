# 学术日历与地图

## 实现功能

- [x] 用户管理

- [x] 会议管理

- [x] 会议加入到用户日程中

- [x] Google Map和Google Calendar调用

## 技术栈

* SpringBoot + MyBatis + MYSQL + Google API
* 具体技术选型：
* 中间件：Kafka，Redis，Elasticsearch
  * 前端：bootstrap，jquery，ajax，thymeleaf
  * 后端：spring，springboot，mybatis
  * 数据库：mysql，redis



## 开发日志

* 3-9： 前端静态页面完成
* 3-16：前端完善+登录注册功能完成
* 4-16：数据源获取完成
* 4-20：会议录入部分完成
* 4-22：会议检索功能初步完成
* 4-24： 第一次迭代
* 4-27：第一次迭代



## 测试时运行说明
- 运行说明
  - 先运行.SQL文件，建立必备数据库
  - 程序中application.properties中修改相关配置，主要是修改数据库连接相关
  - 运行 SpringBoot 程序，使用 localhost:8080 即可访问
  - 若后期使用 redis，es 等，需先保证中间件运行
- 数据源获取
  - 仓库中的SQL脚本含有数据，若要自己爬取，运行crawl/crawl_core.py，环境是Python3.7，依赖库有bs4和lxml



## 代办：

- [ ] 部分死链和静态页面设计不完善的地方
- [ ] ElasticSearch全文检索部分
- [ ] 权限控制和认证，如部分登录后才能展示页面可以直接访问等，这一部分后期引入spring security可以解决
- [ ] 原来完成的忘记密码邮箱找回暂时搁置，由于想提高效率，需要使用中间件Redis，后期一并加入
- [ ] MQ实现会议到来提醒和会议修改/添加提醒等部分
- [ ] 后期加入权限控制后，考虑管理员直接页面接收用户反馈，并且进行修改会议信息部分，再使用MQ处理




## 项目成员

* [jonnyS](https://github.com/JonnyS1226)
* [Clairejfj](https://github.com/Clairejfj)
* [dasklarleo](https://github.com/dasklarleo)

