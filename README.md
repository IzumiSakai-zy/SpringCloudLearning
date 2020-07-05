# SpringCloudLearning
学习SpringCloud

### SpringCloud技术栈

* 服务注册与发现——Eureka
* 服务负载与调用——Ribbon
*  服务熔断降级——Hystrix
* 服务网关——Zuul
* 服务分布式配置——SpringCloud Config
* 服务开发——Springboot

****************

### SpringCloud与SpringBoot的版本依赖关系
* 粗略版本查看
  * 这两个的版本有很大的相关性，不能随便配
  * 版本配置查看`https://github.com/IzumiSakai-zy/SpringCloudLearning.git`网址下面
  * 目前Hoxton——2.2.x
* 详细版本查看
  * 查看网址`https://start.spring.io/actuator/info`获取json格式串
  * 把json格式串拿去在线网站解析来查看版本`"Hoxton.SR6": "Spring Boot >=2.2.0.M4 and <2.3.2.BUILD-SNAPSHOT", `
* 官方文档推荐搭配版本
  
  * `https://cloud.spring.io/spring-cloud-static/Hoxton.SR5/reference/html/` 里有推荐版本搭配
  
  * Release Train Version: Hoxton.SR5   Supported Boot Version: 2.2.1.RELEASE（官方居然给springboot降级了）
  
* 此教程推荐使用版本
  * cloud——Hoxton.SR1
  * boot——2.2.2
  * cloud Alibaba——2.1.0
  * java——8
  * maven——3.5+
  * MySQL——5.7+

****************

### Cloud组件停更、身体、替换

* 服务注册中心——旧：Eureka、新：Alibaba Nacos
* 服务调用——旧：Ribbon、新：LoadBlance
* 服务调用2——旧：Feign、新：OpenFrign
* 服务熔断降级——旧：Hystrix、新：Alibaba sentinel
* 服务网关——旧：Zuul、新：gateway
* 服务配置——旧：config、新：Alibaba nacos
* 服务总线——旧：Bus、新：Alibaba nacos

### 创建父工程项目

* 创建一个空的项目

* 进入IDEA的file encoding设置所有编码为utf-8，并把旁边的勾打上

* 进入设置开启annotation processors的激活注解

* 把java的编译器设置成8

* 删除所有的文件包括src只留下pom.xml

* 修改pom.xml文件

  * 设置打包方式为pom
  * `<dependencyManagement></dependencyManagement>`标签是管理但不导入

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <groupId>org.weng.springColud</groupId><!--自己的groupId-->
      <artifactId>springColudA</artifactId><!--自己的artifactId-->
      <version>1.0-SNAPSHOT</version>
      <packaging>pom</packaging>
      
      <!-- packaging pom 总工程-->
      <!--统一管理jar包版本-->
      <properties>
          <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
          <maven.compiler.source>12</maven.compiler.source>
          <maven.compiler.target>12</maven.compiler.target>
          <junit.version>4.12</junit.version>
          <lombok.version>1.18.10</lombok.version>
          <log4j.version>1.2.17</log4j.version>
          <mysql.version>5.1.47</mysql.version>
          <druid.version>1.1.16</druid.version>
          <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
      </properties>
  
      <!--子模块继承之后，提供作用：锁定版本+子module不用写groupId和version-->
      <dependencyManagement><!--定义规范，但不导入-->
          <dependencies>
              <!--spring boot 2.2.2-->
              <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-dependencies</artifactId>
                  <version>2.2.2.RELEASE</version>
                  <type>pom</type>
                  <scope>import</scope>
              </dependency>
              <!--spring cloud Hoxton.SR1-->
              <dependency>
                  <groupId>org.springframework.cloud</groupId>
                  <artifactId>spring-cloud-dependencies</artifactId>
                  <version>Hoxton.SR1</version>
                  <type>pom</type>
                  <scope>import</scope>
              </dependency>
              <!--省略-->
      </dependencyManagement>
      <!--热启动插件-->
      <build>
          <plugins>
              <plugin>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
                  <configuration>
                      <fork>true</fork>
                      <addResources>true</addResources>
                  </configuration>
              </plugin>
          </plugins>
      </build>
  </project>
  ```