# xiaohua_mm

## 1. 概述

### 1.1 案例介绍

**小花面面是一款面向程序员的面试刷题系统**，服务于学员培训学习完毕后的复习问题，通过大量刷题，提高企业面试题的熟知度，辅助学员顺利完成求职面试。

注意：我只做了小花面面系统的一部分，对有些功能进行了微缩改造，

小花面面本原始系统又分为三个子系统：**后台系统，前台系统，手机端**（在本课程中不涉及），以下是各子系统核心的功能介绍

**后台系统**：试题的录入

**前台系统**：会员刷题

**手机端**：会员刷题（常用/主流）

### 1.2系统架构

接下来在做之前，我们就要说说这套案例制作的时候应该采用怎样的一种结构进行搭建？同时采用什么样的技术进行实现。那么首先我们先聊一聊整个项目的系统架构。

![image](https://user-images.githubusercontent.com/84114204/161427673-60c39c97-c56c-47fd-8712-bae4c8528d52.png)

对于整个系统来说，它分成两块，一个是后台系统，一个是前台系统。那么我们在访问后台系统的时候，是通过浏览器来进行访问，最终把我们的数据存入到我们的数据库端。记得一点，我们从后台系统录入的题目数据，最终会被前台系统使用，所以说前后台系统，他们在数据这一端上来说是进行共享的。也就是前后台系统用的基础数据是同一组。那么前台系统是通过手机端来进行刷这个题，那么我们在这里边呢，不做手机端的，我们也做浏览器的，这就是它的一个整体的结构，你要先了解。

那么对于后台系统来说，开发的时候，我们采用三层架构的形式开发，分为表现、业务、数据。表现层负责数据的收集以及回显，业务层负责业务逻辑处理，数据层负责与数据库打交道。那么对于前台系统来说呢，它仍然是这样的，只不过他们之间用的技术有差别。那么都有哪些差别呢？接下来咱们就要来说一下技术架构！

### 1.3 技术架构

对于后台系统与前台系统，我们分成五个层面来介绍他们的产品。分别是页面端的技术，也就是我们的前端技术了，以及controller、service、Dao、DB。

详情见下图：

![image](https://user-images.githubusercontent.com/84114204/161427684-2c258935-1830-4da2-ba3f-656643b04715.png)

AdminLTE：一个前端框架，提供了很多友好的主题样式，动态功能效果，可直接使用，非常方便

POI：数据报表工具，可用于报表导出

### 1.4 需求分析

刷题是整个项目的核心功能，那么试题一定是我们的核心。对一道题来说，体型会多种多样，这次我们以最复杂的选择题来演示。选择题一般由题目与选项构成，题目与选项是一对多的关系。在下图中，我们以线和圆点来表述它们之间的关系。没有圆点的是“一”方，有圆点的是“多”方。

继续看，试题一定有归属的学科，比如你Java的同学做python的题，其实意义不大对吧。题目和学科能直产生一对多的关系吗？一个学科下其实分了很多的东西，比如Java下分Java基础、JavaWeb等等，所以在学科和试题建，需要有个目录。

假如你现在想去一个企业，是不想想看看这个企业以前都出些什么面试题呀。那就需要一个企业的模块了。一个企业与试题之间，也是一对多的关系。

试题是谁录入系统呢？需要有用户模块，那肯定是操作系统的人，对于这个用户来说，并不是所有人都能录入，所以需要约定一个部门。然后，那是部门中所有人都需要录入么？这样就涉及到了一个权限的问题了，我们说你这个用户有一种角色，就能拥有录入试题的权限！所以在用户与角色之间形成一个多对多的关系。

这个人分配角色了就能录试题了吗？不，还需要一个叫模块的东西。就是这个系统中一共有多少种操作？在我们系统中有一个模块叫做录入试题的模块，有一个模块叫审核试题的模块，是这个角色能操作这个模块儿，所以这个用户才能执行这项操作。模块与角色之间也是一个多对多的关系。

录入完了就能直接用么？不能，万一你录的题有问题呢？所以一定要有一个审核机制。对于所有的操作，我们都需要有一个日志来记录了，所以还要有一个日志的东西。

![image](https://user-images.githubusercontent.com/84114204/161427722-72ced719-a632-40d2-8eda-f2554a6faa7b.png)


接下来要开始答题 ，那谁来答题呢？，会员。所以我们要有一个会员的模块。会员就直接做题吗？，做题应该是以试卷的形式呈现。作为一个会员，登录以后，你要去做一套卷子，而不是做一道题，当然你说能不能做单个题，可以，可以把单个题理解为这个试卷就一道题。会员与试卷是一对多的关系。

那试卷就与我们的试题直接产生关系么，不需要。我们每一个试卷生成以后。都需要把这个题给做出来，你做出来以后，除了有题目以外，试卷中还得有你做题的答案。所以说试卷中会保存一个试卷的答题明细，这个地方试卷对答题明细是一个一对多的关系。其实答题明细中本身就有试题的ID，因此我们这里用试卷与答题明细对试题进行关联。

总结一下，左边这块是属于后台系统，负责保障录入试题的。右边这块属于前台系统，负责学员的刷题功能。

## 2. 环境搭建

### 2.1 工程结构搭建

创建工程的要求，及注意点：

- 创建maven工程（web工程）


- 导入项目依赖的坐标（资源）


- 补全目录结构

>web
>
>​    |-----src
>
>​	       |-------main
>
>​			      |------------java
>
>​	                      |------------resources
>
>​                              |------------webapp
>
>​                |-------test
>
>​			      |-------------java
>
>​			      |-------------resources



- 创建三层架构开发的包层次结构


- 创建三层架构开发的包层次结构

>domain
>
>dao
>
>service
>
>web
>
>​       controller
>
>​	filters
>
>utils
>
>factory


创建相关目录结构，包结构，如下

![image](https://user-images.githubusercontent.com/84114204/161427769-6be6a355-0152-4938-a755-e93a9031855c.png)

删除：web.xml


### 2.2 页面结构搭建

管理后台一般有着固定的页面构建模式，我们可以进行快速构建

- AdminLTE是一款建立在bootstrap和jquery之上的开源模板主题工具，其中内置了多个模板页面，可以用于快速创建响应式Html5网站，并免去了书写大量的 CSS 与 JS 的工作


由AdminLTE构建的网站后台的整体页面布局如下：

![image](https://user-images.githubusercontent.com/84114204/161427797-5b4925e3-1d48-4b77-8170-75b18eb1d1b2.png)

## 3. 企业模块

我们选择一个单表的增删改查功能来进行入门，熟悉开发的模式和流程，因此选择企业模块

![image](https://user-images.githubusercontent.com/84114204/161427802-aabf7536-8775-43cd-99bb-f1c08b2be4f3.png)

要对企业信息做CRUD，我们需要知道要操作企业的那些字段，

![image](https://user-images.githubusercontent.com/84114204/161427811-4eee62e5-9c54-4a19-bebd-ff96a808be70.png)

### 3.1 数据层开发

1：创建实体：com.huahua.domain.store.Company

2：创建dao：com.huahua.dao.store.CompanyDao


**总结**：

![image](https://user-images.githubusercontent.com/84114204/161427825-d0b25fe4-fbb2-46d9-8b3d-610ef47bd407.png)



### 3.2 业务层开发

业务层基础功能：

增

删

改

查单个

查全部

分页查（分页插件）



我们依次来实现

1：创建业务层接口：com.huahua.service.store.CompanyService

2：创建业务层实现类：com.huahua.service.store.impl.CompanyServiceImpl

3：创建测试类，对业务层方法依次测试，在测试包下创建：com.huahua.service.store.CompanyServiceTest


### 3.3 表现层列表功能

功能分析：

![image](https://user-images.githubusercontent.com/84114204/161427841-1ec3dcc7-9f3f-4b45-b6f2-2d222d1f66ac.png)


1：创建Servlet：com.huahua.web.controller.store.company.CompanyServlet

2：优化doGost方法，添加分页控制

### 3.4 表现层添加功能

1：为了应对更多的方法，我们进行方法抽取

### 3.5 表现层删除修改功能

修改和保存差异不大，一个是做save，一个是做update，

1：在doGet方法添加去到修改页面和真正修改的两个方法

2：删除功能后台相对简单，主要是前台对要删除数据的id如何获取，对于后台，在doGet方法中添加对删除操作的判断，并添加删除方法

3：代码优化，在所有的操作方法中我们要调用业务层，我们都要去创建业务层对象，每个方法中都去创建略显复杂，怎么办？

创建一个BaseServlet：com.huahua.web.controller.BaseServlet

4：修改`CompanyServlet`让其继承自`BaseServlet`，然后在各个方法中注释掉业务层对象创建的代码.

## 4. 部门模块

对于部门，我们要弄清楚的部门的结构


### 4.1 部门模块单表开发

1：创建实体：com.huahua.domain.system.Dept

2：在`src/main/resources`下创建目录`com/huahua/dao/system`

3：创建dao接口：com.huahua.dao.system.DeptDao

4：创建业务层接口：com.huahua.service.system.DeptService

5：创建业务层实现类：com.huahua.service.system.impl.DeptServiceImpl

6：创建Servlet：com.huahua.web.controller.system.DeptServlet

7：在BaseServlet中添加新的代码.

8：创建目录`WEB-INF/pages/system/dept`.



### 4.2 部门自连接


1：在实体中添加自关联的字段，parent

2：修改映射配置，找到`src/main/resources/com/huahua/dao/system/DeptDao.xml`，添加关联映射

```xml
<!--配置实体类属性和数据库表中列的对应关系-->
<resultMap id="BaseResultMap" type="com.huahua.domain.system.Dept">
    <id column="dept_id" jdbcType="VARCHAR" property="id"/>
    <result column="dept_name" jdbcType="VARCHAR" property="deptName"/>
    <result column="parent_id" jdbcType="VARCHAR" property="parentId"/>
    <result column="state" jdbcType="DECIMAL" property="state"/>
    <!--关联关系-->
    <association
                 property="parent"
                 javaType="com.huahua.domain.system.Dept"
                 column="parent_id"
                 select="com.huahua.dao.system.DeptDao.findById"
                 />
</resultMap>
```

3：新建的时候要查询所有部门数据，装载到页面上

找到：DeptServlet中的toAdd方法，添加查询部门信息的代码

4：同理找到DeptServlet中的toEdit方法，添加查询部门信息的代码


## 5. 用户模块

1：创建实体：com.huahua.domain.system.User

2：创建dao接口：com.huahua.dao.system.UserDao

3：添加映射配置文件.

4：创建业务层接口：com.huahua.service.system.UserService

5：创建业务层实现类：com.huahua.service.system.impl.UserServiceImpl

6：创建servlet：com.huahua.web.controller.system.UserServlet

7：修改BaseServlet


8：创建`WEB-INF/pages/user`.

注意：在此处希望大家是自行的拷贝之前dept的页面然后自己修改

9：新建功能时，去新建页面时需要查询部门信息，修改toAdd方法

10：新建用户时，用户的密码需要加密

11：修改业务层代码，找到：save方法，给密码加密完成后再存入数据库

12：修改时，在去修改页面是，需要加载部门信息，找到UseServlet的toEdit方法，

13：真正在修改时，我们需要在业务层做一些处理，找到用户的业务层实现类，修改update方法

我们采用方案二：找到用户对应的xml配置文件：`UserDao.XML`，找到update标签，做出修改

```xml
<!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
<update id="update" parameterType="com.huahua.domain.system.User">
    update ss_user
    set user_name = #{userName,jdbcType=VARCHAR},
    state = #{state,jdbcType=DECIMAL},
    gender = #{gender,jdbcType=CHAR},
    telephone = #{telephone,jdbcType=VARCHAR},
    dept_id = #{deptId,jdbcType=VARCHAR}
    where user_id = #{id,jdbcType=VARCHAR}
</update>
```

