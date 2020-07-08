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
      <!-- packaging pom 总工程-->
      <packaging>pom</packaging>
  
      <!--新建的模块-->
      <modules>
          <module>cloud-provider-payment8001</module>
      </modules>
  
  
      <!--统一管理jar包版本-->
      <properties>
          <!--java版本自己增加-->
          <java.version>1.8</java.version>
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
              <dependency>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-project-info-reports-plugin</artifactId>
                  <version>3.0.0</version>
              </dependency>
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
              <!--spring cloud 阿里巴巴-->
              <dependency>
                  <groupId>com.alibaba.cloud</groupId>
                  <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                  <version>2.1.0.RELEASE</version>
                  <type>pom</type>
                  <scope>import</scope>
              </dependency>
              <!--mysql-->
              <dependency>
                  <groupId>mysql</groupId>
                  <artifactId>mysql-connector-java</artifactId>
                  <version>${mysql.version}</version>
                  <scope>runtime</scope>
              </dependency>
              <!-- druid-->
              <dependency>
                  <groupId>com.alibaba</groupId>
                  <artifactId>druid-spring-boot-starter</artifactId>
                  <version>1.1.14</version>
              </dependency>
  <!--            <dependency>-->
  <!--                <groupId>com.alibaba</groupId>-->
  <!--                <artifactId>druid</artifactId>-->
  <!--                <version>${druid.version}</version>-->
  <!--            </dependency>-->
  <!--            &lt;!&ndash;mybatis&ndash;&gt;-->
  <!--            <dependency>-->
  <!--                <groupId>org.mybatis.spring.boot</groupId>-->
  <!--                <artifactId>mybatis-spring-boot-starter</artifactId>-->
  <!--                <version>${mybatis.spring.boot.version}</version>-->
  <!--            </dependency>-->
              <dependency>
                  <groupId>com.baomidou</groupId>
                  <artifactId>mybatis-plus-boot-starter</artifactId>
                  <version>3.3.2</version>
              </dependency>
              <dependency>
                  <groupId>com.baomidou</groupId>
                  <artifactId>mybatis-plus-generator</artifactId>
                  <version>3.3.2</version>
              </dependency>
              <dependency>
                  <groupId>org.apache.velocity</groupId>
                  <artifactId>velocity</artifactId>
                  <version>1.7</version>
              </dependency>
              <!--junit-->
              <dependency>
                  <groupId>junit</groupId>
                  <artifactId>junit</artifactId>
                  <version>${junit.version}</version>
              </dependency>
              <!--log4j-->
              <dependency>
                  <groupId>log4j</groupId>
                  <artifactId>log4j</artifactId>
                  <version>${log4j.version}</version>
              </dependency>
          </dependencies>
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
******************
### 构造支付模块
* 构建模块

  * 五步走：建Module、改pom、写yml、主启动、业务类 
  
* 构造完成后父工程的pom文件会添加一个modules标签

  ```xml
  <!--新建的模块-->
  <modules>
      <module>cloud-provider-payment8001</module>
  </modules>
  ```
  
* 修改子项目的pom文件
  
  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>springColudA</artifactId>
          <groupId>org.weng.springColud</groupId>
          <version>1.0-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
  
      <artifactId>cloud-provider-payment8001</artifactId>
  
      <dependencies>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-actuator</artifactId>
          </dependency>
          <dependency>
              <groupId>com.baomidou</groupId>
              <artifactId>mybatis-plus-boot-starter</artifactId>
          </dependency>
          <dependency>
              <groupId>com.alibaba</groupId>
              <artifactId>druid-spring-boot-starter</artifactId>
          </dependency>
          <dependency>
              <groupId>com.baomidou</groupId>
              <artifactId>mybatis-plus-generator</artifactId>
          </dependency>
          <dependency>
              <groupId>org.apache.velocity</groupId>
              <artifactId>velocity</artifactId>
          </dependency>
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-jdbc</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-devtools</artifactId>
          </dependency>
          <dependency>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
          </dependency>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
          </dependency>
      </dependencies>
  </project>
  ```
  
* 改yml

  ```yaml
  server:
    port: 8001
    
  spring:
    datasource:
      username: root
      password: 542270191MSzyl
      url: jdbc:mysql://localhost:3306/mybatis-plus?useUnicode=true&characterEncoding=utf8
      type: com.alibaba.druid.pool.DruidDataSource
      druid:
        initial-size: 5
        min-idle: 5
        max-active: 20
        max-wait: 40000
        time-between-eviction-runs-millis: 60000
        min-evictable-idle-time-millis: 30000
        validation-query: selcet 1 from dual
        test-while-idle: true
        test-on-borrow: false
        test-on-return: false
        pool-prepared-statements: true
  ```
  
* 写主启动

  ```java
  @SpringBootApplication
  public class PaymentMain8001 {
      public static void main(String[] args) {
          SpringApplication.run(PaymentMain8001.class,args);
      }
  }
  ```
  
* 写业务层
  
  * 自定义一个通用返回结果类CommonResult
  
    * 使用lombok用惯了，有些时候自定义的类忘写@Data注解而又没写set方法就会报错，而且错误很难找
    
    ```java
    @Data
    public class CommonResult<T> {
        private Integer code;
        private String message;
        private T t;
    
        public CommonResult(Integer code, String message, T t) {
            this.code = code;
            this.message = message;
            this.t = t;
        }
    
        public CommonResult(Integer code, String message) {
            this(code,message,null);
        }
    
        @Override
        public String toString() {
            return "CommonResult{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    ", t=" + t +
                    '}';
        }
    }
    ```
    
  * 写业务层
  
    * 业务层可以返回自己的编写的类
    * 业务层顶层最好有一个前缀路径
    * 业务层都是@RestController。可以返回自定义结果类也可返回ModelAndView
    * 前后端分离后端只管传数据，而且传的数据都是json字符串。因此自定义结果类用处可能很大
  
    ```java
    @RestController
    @RequestMapping("/payment")
    public class PaymentController {
        @Autowired
        PaymentServiceImpl paymentService;
    
        @GetMapping("/payments/{id}")
        public CommonResult<Payment> query(@PathVariable Integer id){
            Payment payment = paymentService.getById(id);
            if (payment==null)
                return new CommonResult(404,"查询失败");
            else
                return new CommonResult(200,"查询成功",payment);
        }
    }
    ```
  
*************************

### 热部署Devtools

* 方便是方便，，对电脑要求高，就不用了

***********************
### 消费者模块构建

* 总体方法同上

* 注意事项
  * 要先创建主类，在写yml，不然没有提示
  * 消费者模块的entities和支付模块是相同的

* `RestTemplate`的使用

  * 首先通过配置类注入到spring中 

    ```java 
    @Configuration
    public class MianConfig {
        @Bean(name = "restTemplate")
        public RestTemplate getRestTemplate(){
            return new RestTemplate();
        }
    }
    ```
  
*************************
### 模块之间读操作互相调用

* 80端口消费者controller编写

  * 使用RestTemplate来进行调用访问
  * 2个参数(URL值，访问服务返回值类型)
* 消费者访问 localhost//consumer/1
  
  ```java
  @RestController
  @Slf4j
  @RequestMapping("/consumer")
  public class OrderController {
      private static final String PAYMENT_URL="http://localhost:8001";
      @Autowired
      private RestTemplate restTemplate;
  
      @RequestMapping("/{id}")
      public CommonResult<Payment> find(@PathVariable("id") Integer id){
          return restTemplate.getForObject(PAYMENT_URL + "//payment/payments/"+id.toString(), CommonResult.class);
      }
  }
  ```
  
* 注意事项

  * 要把两个端口同时打开
  * 返回的类型一定要有无参数构造函数

******************

### 模块之间写操作调用

* 消费者代码

* 支付模块代码

* 注意事项

  * 写操作是使用postForObject()方法。有三个参数(URL，post的对象，返回值类型)

  * 写操作访问的@RequestMapping中不要带参数，是实际来浏览器中访问才带参数
  * 支付者模块参数要加@RequestBody注解，因为这个参数是从消费者的访问传过来的