该项目名称：WEB即时聊天系统

使用spring boot 和 vue 对项目进行重构和扩展：https://github.com/xiaotanwo/chat

B站演示视频：https://www.bilibili.com/video/BV1Vh411R7QE/

功能有：登录、注册、聊天、退出登录、拦截用户的请求，只允许访问静态资源/static、登录、注册、后台出错时（抛出异常时）返回的jsp，其余功能需要先登录才能访问。

开发路线：

1、建表

2、编写pom.xml（导入需要的包，写resource，将.properties和.xml文件一并生成到target目录中）

3、编写springmvc的配置（dispatcherServlet.xml：组件扫描器（controller）、视图解析器、注解驱动，访问静态资源、拦截器、异常抛出处理）

4、编写jdbc.properties配置（mysql数据库的名称、用户和密码）

5、编写mybatis的配置（设置别名和指定mapping文件路径）

5、编写spring的配置（加载数据库属性文件，连接数据库（用的是阿里的druid），创建SqlSessionFactoryBean对象和dao对象，组件扫描器（service），创建MultipartResolver文件解析器）

6、编写web.xml（注册中央调度器、注册spring监听器、注册字符集过滤器）

注：有点配置是需要的时候配上的，顺序有所颠倒

7、创建相关的包controller、dao、domain、service、handler

8、编写代码（主要工作）

9、导入static静态资源


开发环境：

系统：windows10

编译器：idea

环境：

tomcat 9.0.36

mysql 5.1.73

框架：SSM


使用前需做：

1、建表

建表语句：

user表

CREATE TABLE `user` (

  `id` int(11) NOT NULL AUTO_INCREMENT,
  
  `name` varchar(80) NOT NULL,
  
  `password` varchar(80) NOT NULL,
  
  `image` varchar(80) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `name` (`name`) USING BTREE
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8

chat表

CREATE TABLE `chat` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `userId` int(11) NOT NULL,

  `content` varchar(1000) DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `userId` (`userId`),

  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8

2、修改src/main/resources/conf/mybatis.xml中的mysql属性，如数据库的位置和名称、用户、密码
