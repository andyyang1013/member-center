# 会员中心 #
该项目共包含4个模块，公共模块、管理后台、接口服务以及客户端，其中管理后台和接口服务需要依赖公共的模块core，客户端模块通过http协议访问接口服务。

<a name="获取代码"></a>
## 获取代码【SVN代码管理】
+ 分支主目录：https://192.168.74.138:8080/svn/lewin/01Code/branch/member-center
+ 主版本目录：暂未定义
 

## 环境要求 ##

+ JDK 1.8
+ Redis 3.2.9
+ Mysql 5.7
+ lombok 插件


## 技术栈 ##

+ Spring Boot
+ Spring Cache
+ Redis 
+ Pagehelper
+ Druid
+ Log4j2
+ Mybatis-plus

## 框架功能点 ##

+ 分布式全局唯一ID，由mybatis-plus提供工具类 IdWorker 处理
+ 分布式session共享(redis共享)
+ 返回消息统一封装
+ 异常信息统一处理
+ Cors解决跨域
+ 登录拦截器、异常拦截器
+ 日期格式支持多种转换格式："yyyy", "yyyy-MM", "yyyy-MM-dd",  "yyyy-MM-dd HH:mm" ,"yyyy-MM-dd HH:mm:ss"
+ 多环境配置
+ 基本工具类:com.hhly.member.util包下
   
## maven打包命令 ##

+ mvn clean package -Dmaven.test.skip=true  -U
 
## 业务需求 ##

 

 










