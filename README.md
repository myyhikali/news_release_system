# 网页版新闻管理系统

## 功能设计

- 管理员
    - 栏目管理页面
        - 可增加栏目
        - 可删除，修改栏目，同时修改和删除该栏目下所有文章关于栏目类别的标签
    - 编辑分配页面
        - 处理编辑人员上传的文章，为他们确定相应的类别
        - 可退回不合格的文章，但是不能修改编辑者的文章
    - 文章管理页面
        - 可查看当前所有的文章，除去编辑者未上传的文章   
    - 文章修改记录页面
        - 可查看所有文章的历史状态修改
- 编辑人员
    - 编辑页面
        - 可编辑和上传文章
        - 可保存未上传文章
    - 编辑文章管理页面
        - 对保存未上传文章进行编辑
        - 对退回文章重新编辑
- 游客
    - 浏览页面
        - 可浏览不同栏目的文章（管理员和编辑人员也具有该功能）
- 登陆页面
    - 管理员登录
    - 编辑人员登录
    - 游客无需登录
- 注册页面
    - 管理员和编辑人员注册


## 框架

- 前端 Vue Bootstrap ajax axios http-server
- 后端 Spring SpringMVC Mybatis
- 数据库 MySql

## 项目架构

### 后端ssm目录

- filter
    - CorsFilter 在有登录验证的response头中添加有关跨域访问，cookie使用的声明

- interceptor
    - EditorInterceptor 关于编辑者功能使用中对编辑者身份进行验证的拦截器
    - ManagerInterceptor 关于管理者功能使用中对编辑者身份进行验证的拦截器

- listener
    - SessionListener 监听会话的创建和销毁，sessionMap用作做会话存储

- controller
    - ArticleController
    - ColumnController 
    - EditorController
    - ManagerController
    - UserController

- service
    - ArticleService
    - ColumnService 新闻栏服务
    - PictureService 新闻封面图片服务
    - UserService 用户相关服务
- resource
    - applicationContext.xml spring容器有关配置
        - dataSource Mybatis连接配置
        - transactionManager 事务管理
        - sqlSessionFactory
        - multipartResolver 用于解决文件上传
    - db.properties 数据库有关设定值
    - mybatis-config.xml mybatis配置
    - spring-mvc.config springmvc有关配置
        - Interceptor
    - web.xml
        - CorsFilter
        - SessionListener
        - springmvc servlet

### 请求接口url

|使用者|功能|类型|url|
|--|--|--|--|
|管理员|
||获取所有发布的文章| GET| http://localhost:10080/manage/article|
||修改文章状态|PUT|http://localhost:10080/manage/article/aid/state|
||查看文章状态修改记录 |GET| http://localhost:10080/manage/articlemodify/
||修改栏目内容| PUT| http://localhost:10080/manage/modifycolumn?cid=xx&cname=xxx
||增加栏目|POST|http://localhost:10080/manage/addcolumn?cname="+cname
||删除栏目|DELETE|http://localhost:10080/manage/deletecolumn?cid="+cid
|||||
|编辑者|
||编辑文章时获取之前保存的文章|GET| http://localhost:10080/editor/savedarticle/aid
||上传封面图片|POST|http://localhost:10080/editor/uploadpicture  formdata（file）
||保存编辑文章|POST|http://localhost:10080/editor/save/栏目id/?state="+ state+"&title="+title+"&filename="+file.name   data:json（文章主题内容）|
||保存修改的编辑内容|POST| http://localhost:10080/editor/modify/栏目id/?state="+ state+"&title="+title+"&filename="+file.name data:json（文章主题内容）|
||修改文章状态|PUT|http://localhost:10080/editor/article/文章id/state|
||获取当前编辑者的文章|GET|http://localhost:10080/editor/article|
|||||
|ALL|
||获取指定文章的封面图片|GET|http://localhost:10080/article/picture/
||获取栏目信息|GET|http://localhost:10080/columns
||验证登录|POST|http://localhost:10080/user/checklogin
||注册|POST|http://localhost:10080/user/register formdata
||获取栏目下的文章|GET|http://localhost:10080/columns/栏目id
||获取栏目下最新的文章 |GET| http://localhost:10080/columns/栏目id/latest
||获取指定文章的详细内容 |GET |http://localhost:10080/article/read/
||注销登录|GET|http://localhost:10080/user/logout
||获取登录的用户名|GET|http://localhost:10080/user/username



### http-server目录

![](pic/web.png)

### 请求跨域
- ssm服务端开设在本地10080端口
- http-server开设在本地8080端口
- 无论是文件上传请求还是各种业务请求都涉及到跨域。需要提供跨域支持，又要保持sessionid，后端需要每次都需要根据请求域在response头中加上允许访问的请求域，设定允许的头，设定Access-Control-Allow-Credentials为true，以及设定编码返回格式等。
- ajax请求需要加上xhrFields: {withCredentials: true}，使得cookie能够加入到请求头中。

### 关于cookie和session
- cookie 是访问过的网站创建的文件，用于存储浏览信息，例如个人资料信息，保存在浏览器中。
- cookie保存内容
    - SESSIONID 。保存SESSIONID至cookie，请求附带cookie，后端可以根据SESSIONID拿到指定的session。
    - level（用户等级）。由于项目为半前后端分离，前端需要根据响应来进行渲染。保存level（用户等级）是为了详情操作显示栏只显示当前用户等级支持的操作。比如管理员界面包含栏目管理和全部文章管理。编辑者涉及到文章状态管理，文章创建，文章编辑等。
- session存储在服务器端
- session保存内容
    - uid 服务端保存的当前用户id
    - uname 服务端会话中保存的uname，网页通过登录认证后，可以通过请求的方式获取uname。
    - level 涉及到登录用户的等级，为操作再打一层认证保险。

### 事务设计
- 建立TransactionService类，包含复杂的服务，需要具有异常回滚的功能。而事务通常切于service层，而不是controller层。
- 事务配置：采用注解配置，置于applicationContext，也就是由spring来管理transactionManager，而不是springMVC。若切于controller，配置声明需放在springMVC-config中

### 文件上传
- java端支持：org.springframework.web.multipart.commons.CommonsMultipartResolver
    - 注册在applicationContext中
    - @RequestParam(value="file",required=false) MultipartFile uploadFile
    - 文件保存:uploadFile.transferTo(newFile)
- 前端
    - html元素支持：```<input type="file" class="file col-lg-4" id="uploadfile" style="width: 10px;height: 10px">```
    - 请求表单类型：file

### 封面图片转码 文章内容存储
- 图片的保存格式 longblob
- 文章内容的保存格式 longblob

- 图片前端的显示
    - 通过img标签的src属性设置为接口url获取封面图片：http://localhost:10080/article/picture/文章id
    - 后端通过OutputStream将byte[]返回给前端页面

- 文字内容的显示
    - 通过制定元素的innerHtml设定文章内容
    - 后端对byte[]进行转码，转为String后返回给前端


## 数据库设计

- 文章管理表

名称|类型|备注
--|:--:|--:
aid|int|文章ID
title|int|文章标题
content|longblob|文章内容
eid|int|编辑者ID
cid|int|栏目ID
state|char[20]|文章状态，checked:上传并通过,published:上传等待审核,returned:未通过审核，被退回；saved:保存未上传
time|timestamp|文章通过时间


- 文章图片管理表

名称|类型|备注
--|:--:|--:
pid|int|图片ID
aid|int|所属文章ID
pic|longblob|图片存储
pname|varchar|图片名称

- 文章状态修改日志表[多对多表]

名称|类型|备注
--|:--:|--:
mid|int|修改ID
mtime|timestamp|修改时间
uid|int|修改ID
estate|char[20]|修改状态
aid|int|文章ID

- 管理员&编辑人员管理表

名称|类型|备注
--|:--:|--:
uid|int|管理员&编辑人员ID
uname|char[20]|用户名
pwd|char[20]|登录密码
level|int|1:管理员。0:编辑人员

- 栏目管理表

名称|类型|备注
--|:--:|--:
cid|int|栏目ID
cname|char[20]|栏目名称
uid|int|最后一个修改栏目属性的管理员
createtime|Date|创建时间



## github地址

https://github.com/myyhikali/news_release_system


![picture](pic/p1.png)
