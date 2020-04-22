# 学术日历与地图

## 实现功能

- [x] 用户管理

- [x] 会议管理

- [ ] 会议加入到用户日程中

- [ ] Google Map和Google Calendar调用

## 技术栈

* SpringBoot + MyBatis + MYSQL + Google API
* 待考虑技术和中间件

  * [ ] ElasticSearch全文检索
  * [ ] Redis做一层缓存
  * [ ] 若最终部署，考虑nginx负载均衡，以及部分设想高并发条件下的优化



## 开发日志

* 3-9： 前端静态页面完成
* 3-16：前端完善+登录注册功能完成
* 4-16：数据源获取完成
* 4-20：会议录入部分完成
* 4-22：会议检索功能初步完成
* 4-24： 第一次迭代



## 测试时运行说明

- 先运行.SQL文件，建立必备数据库
- 程序中application.properties中修改相关配置，主要是修改数据库连接相关
- 运行 SpringBoot 程序，使用 localhost:8080 即可访问
- 若后期使用 redis，es 等，需先保证中间件运行



## 项目成员

* [jonnyS](https://github.com/JonnyS1226)
* [Clairejfj](https://github.com/Clairejfj)
* [dasklarleo](https://github.com/dasklarleo)

