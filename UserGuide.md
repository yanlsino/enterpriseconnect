### System Required ###
  1. Java Runtime Environment 1.5.0 +
  1. Database (Mysql Postgresql Oracle etc...), MySQL recommended
  1. Servlet Container (Jetty Tomcat JBoss etc...), Tomcat recommended
  1. If install from source code, Maven 3.0 + is required

### Installation ###

#### 1.从源码编译安装 ####
  1. 从 Google Code SVN 中检出源代码到你本地磁盘
  1. 从你的终端 cd 到源代码的根目录，然后根据自己的数据库选择执行如下命令：
```
  mvn package -P mysql -Djdbc.username={your database's username} -Djdbc.password={your database's password}
  mvn package -P postgresql -Djdbc.username={your database's username} -Djdbc.password={your database's password}
```
  1. 当你成功执行上面的命令之后，你就可以在 ${CONNECT\_ROOT}/connect-web/target 下找到编程完的 war 包 connect-web-${version}.war 编译完的 war 包跟从 Google Code Downloads 里下载的是相同的

#### 2.直接下载 War 安装 ####
  1. 从 Google Code Downloads 中下载最新版本的 War 包，然后部署到 Tomcat 的 webapp 下
  1. 启动 Tomcat，Tomcat 就会将 War 解包部署。当 Tomcat 解包之后你需要根据自己的运行环境进行配置，具体配置参看 ${tomcat\_root}/webapps/connect-web/WEB-INF/classes 下的 [application.properties](application_properties.md) 文件。

### After Installation ###
> 当按照上面的两种方式安装完后，访问 `http://localhost:8080/${context_path`} 时，出现的只是一个主页，没有任何内容。你还需要通过下面几步来配置一个最简单的站点（Enterprise Connect 支持多站点）。
  1. 访问 http://localhost:8080/${context_path}/system 进入后台配置管理界面
  1. 通过站点管理来新增一个站点，例如： 域名 localhost 端口 8080 上下文 connect-web。例如 : [添加站点](#Create_Site.md)
  1. 当完成添加一个新站点之后，在站点列表里就会显示该站点，接下来你就需要对该站点进行具体配置，例如： 菜单配置 模块配置 权限配置等。
  1. 你第一个需要创建的是成员菜单项，在Enterprise Connect中必须拥有该菜单项，当然，有的站点不希望显示该菜单项，你可以通过不起用该菜单项来满足你的需求。例如：[菜单创建](#Create_Menu.md)
  1. 通过角色管理为当前站点创建3个角色。例如： [角色创建](#Create_Role.md)
  1. 权限管理，在进行权限权限管理之前你得先进行资源同步，通过资源同步可以加载当前系统的受控资源。当完成资源同步之后就可以进行权限管理了。例如：[权限管理](#Manage_Permission.md)
  1. 还有最后一步，模板管理，通过模板管理，可以添加自定义模块模板，也就将Enterprise Connect当前支持的这些模块（或者称作 Features）跟具体的分类菜单挂钩。例如：[模板创建](#Create_Template.md)
  1. 最终看到的结果 例如：[例子站点](#Demo_Site.md)

#### Create Site ####
![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/add-site.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/add-site.png)

![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-site.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-site.png)

#### Create Menu ####
![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/add-menu.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/add-menu.png)

![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-category.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-category.png)

#### Create Role ####
![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/add-role.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/add-role.png)

![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-role.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-role.png)

#### Manage Permission ####
![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-resource.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-resource.png)

![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-permission.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-permission.png)

#### Create Template ####
![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/add-template.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/add-template.png)

![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-template.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/list-template.png)

#### Demo Site ####
![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/connect.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/connect.png)

![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/connect-profile.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/connect-profile.png)