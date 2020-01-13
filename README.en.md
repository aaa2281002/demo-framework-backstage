# demo-framework-backstage

**注：如发现项目BUG或好的建议,欢迎各位大佬加入到我的QQ群中告诉我! QQ群号: 616724306** 

#### Description
基于SpringBoot后台管理系统，使用layui

项目实现基础的后台权限功能:

    用户管理
    角色管理
    菜单管理
    按钮管理
    用户角色关联管理
    角色菜单关联管理
    角色菜单按钮关联管理
    系统综合：
        操作日志管理:记录用户登录日志信息
        类型管理：用于系统内部动态类型不用去写枚举类等
        字典管理：处理一些定时任务的key
        前端IP白名单管理：前端和接口白名单限制等
        后台IP白名单管理：后台登录用户白名单限制


#### Software Architecture
Software architecture description
项目采用spring-cloud最新版本2.2.2.RELEASE于2019-10-01日搭建,对应=Hoxton.RELEASE!

暂没使用:同时，为了加快应用的启动，还增加一个全局延迟初始化的配置参数spring.main.lazy-initialization，这可以让我们的应用更快的完成启动动作，但是值得注意的是，延迟启动也会有下面这些副作用：
    
    1.  应用在进行延迟初始化的时候，HTTP请求的处理会需要更长的时间
    2.  原本可能在启动期出现的错误，将延迟到启动的运行期间出现
    
1.  srpingboot,springsecurity,mybatis,thymeleaf,activemq,redis单机、哨兵、集群,html5兼容,layuimini,jquery,mysql5.6
2.  实现主从数据库切换
3.  上传案例在userList.html中，上传页面userUpload.html,窗体全屏打开也是在userList.html页面中
4.  demo-framework.sql初始化数据，库名demoframework
5.  强烈注意：userList.html 页面中的上传不算完善。仅供参考
6.  强烈注意：系统模块中没有用到Cacheable缓存因为使用分布式发布的时候遇到过获取反序列化带JDK某些参数所以造成异常。
7.  图标库：http://www.fontawesome.com.cn/
8.  三个登录页面 login.html=黑客帝国效果， login1.html=粒子吸附， login2.html=layuimini自带登录页面


#### Installation
启动序号    启动模块                                    模块介绍
1.         XXXX-web-backstage                       后台管理启动模块
2.         XXXX-service                             业务实现模块
3.         XXXX-model                               实体类模块
4.         XXXX-dao                                 数据库操作模块
5.         XXXX-common                              公共工具类模块
6.         DB                                       数据库sql文件目录
7.         apache-maven-3.5.4                       maven仓库配置文件(需要修改一下对应盘的存储路径)
8.         XXXX-web-backstage->resources->templates 页面目录
9.         XXXX-web-backstage->resources->static    js、css、image静态文件目录

#### Instructions

1.  请使用JDK8，其他版本JDK造成启动不了，请自行解决
2.  下载后使用Idea开发工具并安装maven3.X以上版本导入代码。如果其他工具打开项目造成的问题请自行解决。
3.  安装MySql5.6版本找到DB目录执行demo-framework.sql文件
4.  修改 application-test.yml 里面 redis单机、数据库库名、账户、密码等
5.  启动之前demo-framework-backstage->Lifecycle=->install编译一下
6.  **启动之前一定要启动redis,注意启动的时候配置的redis模式.需要启动对应模式的redis**
7.  XXXX-web-backstage 启动这个模块的 WebBParentApplication 类中main方法

#### 图
![登录图](https://images.gitee.com/uploads/images/2020/0111/150002_8e058703_803165.png)
![首页图](http://images.gitee.com/uploads/images/2020/0111/150016_6afdfd0e_803165.png)

