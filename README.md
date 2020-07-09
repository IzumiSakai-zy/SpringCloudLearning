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

### 模块之间写操作互相调用

* 消费者代码

  ```java
  @RestController
  @Slf4j
  @RequestMapping("/consumer")
  public class OrderController {
      private static final String PAYMENT_URL="http://localhost:8001";
      @Autowired
      private RestTemplate restTemplate;
  
      @RequestMapping("/insert")
      public CommonResult insert(HttpServletRequest request){
          String serial = request.getParameter("serial");
          Payment payment=new Payment();
          payment.setSerial(serial);
          return restTemplate.postForObject(PAYMENT_URL+"//payment/payments/insert",payment,CommonResult.class);
      }
  }
  ```

* 支付模块代码

  ```java
  @RestController
  @RequestMapping("/payment")
  public class PaymentController {
      @Autowired
      PaymentServiceImpl paymentService;
      
      @RequestMapping("/payments/insert")
      public CommonResult insert(@RequestBody Payment payment){
          boolean save = paymentService.save(payment);
          if (save)
              return new CommonResult(200,"插入成功",payment);
          else
              return new CommonResult(404,"插入失败");
      }
  }
  ```

* 注意事项

  * 写操作是使用postForObject()方法。有三个参数(URL，post的对象，返回值类型)
* 写操作访问的@RequestMapping中不要带参数，是实际来浏览器中访问才带参数
  * 支付者模块参数要加@RequestBody注解，因为这个参数是从消费者的访问传过来的
  * 这种通过地址栏的访问千万不能设置成post方式，就由get方式测试

****************

### 工程重构

* 两个项目都有entities包，而且里面内容是一模一样的。可以打成一个jar包所有模块共享

**************************

### Eureka安装

* 两个组件
  * Eureka Server——提供服务注册服务
  * Eureka Client——通过注册中心进行访问
  
* 导入依赖

  * 注意不是所有模块都要使用数据库，因此有的模块可以不连接数据库

  ```XML
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
  </dependency>
  ```
  
* 改yml

  ```yaml
  server:
    port: 7001
  
  eureka:
    instance:
      hostname: localhost #服务端的实例名称
    client:
      fetch-registry: false # 表示自己是注册中心，不用去检索服务
      register-with-eureka: false # false表示不向注册公司注册自己
      service-url:
        defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
        # 设置与eureka server交互的地址查询服务和注册服务都需要依赖这个网址
  ```
  
* 主类添加`@EnableEurekaServer`注解

  ```java
  @SpringBootApplication
  @EnableEurekaServer
  public class EurekaServerMain7001 {
      public static void main(String[] args) {
          SpringApplication.run(EurekaServerMain7001.class,args);
      }
  }
  ```
  
* 测试

  * 访问 localhost:7001/
  * 建议连上数据库，不然要排除数据库类的自动配置

**********************

### 将支付模块8001加入到Eureka中

* 添加导入依赖

  * 注意一个是server结尾，一个是client结尾

  ```XML
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
  </dependency>
  ```
  
* 主类加上`@EnableEurekaClient`注解

* 改yml

  ```yaml
  spring:
    application:
      name: cloud-payment-service # 注册后的显示的名字
  
  eureka:
    client:
      fetch-registry: true # 自己是客户端，要检索服务
      register-with-eureka: true # 表明向公司注册自己
      service-url:
        defaultZone: http://localhost:7001/eureka/ # 缩进、空格都不能省略
        # 设置与eureka server交互的地址查询服务和注册服务都需要依赖这个网址
  ```
  
* 启动测试

  * 先启动7001Eureka模块
  * 在启动8001支付模块
  * 访问 `http://localhost:7001`
    * 访问结果会添加8001模块到注册环境

***********************

### 将消费者模块80注册到Eureka

* 大体步骤和上面相似
* 访问`localhost//consumer/insert?serial=new_serial`依然可以访问

**************************

### Eureka集群搭建

* 核心：互相注册，相互守望(每台eureka服务器都有其他所有eureka服务器注册信息)

* 修改Windows路径`C:\Windows\System32\drivers\etc\hosts`系统的配置文件，处理多路映射

  * ```bash
    127.0.0.1  eureka7001.com
    127.0.0.1  eureka7001.com
    ```
  
* 如果不做路径映射

  * eureka.instance.hostname应该不是IP地址名称，应该取不一样的值。不能都取为localhost，否则不能构成集群
  
```YAML
  eureka:
    instance:
      hostname: eureka7001 #服务端的实例名称
```

* 修改两个yml文件

  * 7002端口注册到7001服务器，7001端口注册到7002服务器。构成集群
  * 实际上都是localhost，因为只有一台主机。只是加了个映射而已

  ```yaml
  server:
    port: 7002
  
  eureka:
    instance:
      hostname: eureka7002.com #服务端的实例名称
    client:
      fetch-registry: false # 表示自己是注册中心，不用去检索服务
      register-with-eureka: false # false表示不向注册公司注册自己
      service-url:
        defaultZone: http://eureka7001.com:7001/eureka/
        # 设置与eureka server交互的地址查询服务和注册服务都需要依赖这个网址
  ```
  
* 访问测试

  * 打开两个eureka端口
  * 访问`http://eureka7001.com:7001/`DS Replicas指向`eureka7001.com`; 访问`http://eureka7002.com:7002/`DS Replicas指向`eureka7002.com`。实现集群

************************

### 把支付服务和消费者服务注册到2台Eureka服务器构成的集群

* 只用修改yml文件

  * ```yaml
    eureka:
      client:
        service-url:
          defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7001.com:7001/eureka/
    ```
* 测试，一共要开4个端口。先开两个eureka再开两个服务。

***********************

### 服务模块集群构建

* 新建cloud-provider-payment8002项目

* 修改两个模块的controller。用于打印到底调用了那个服务模块

  ```java
  @Value("${server.port}")
  private String serverPort;
  ```
  
* 打开2个eureka服务器，2个service支付模块，一个消费者80

  * 结果都能跑通。eureka注册无问题，支付模块自测无问题，消费者80测试没问题

    * 核心是eureka的service注册名字只有一个**CLOUD-PAYMENT-SERVICE**，但有两个服务

      ```yaml
      spring:
        application:
          name: cloud-payment-service
      ```

  * 但是没有实现负载均衡，永远都是8001.因为`private static final String PAYMENT_URL="http://localhost:8001";`写死了
  
* 实现负载均衡

  * 添加`@LoadBlanced`注解，开启RestTemplate负载均衡

    * 但这是默认的轮询方式，效率不高
  
    ```java
    @Configuration
    public class MianConfig {
        @Bean(name = "restTemplate")
        @LoadBalanced
        public RestTemplate getRestTemplate(){
            return new RestTemplate();
        }
  }
    ```

  * 修改访问路径，整个集群暴露在外就是注册名字
  
  ```java
  private static final String PAYMENT_URL="http://cloud-payment-service";
  ```
  
* 然后就访问`http://localhost/consumer/1`。成功了，会调用不同的service，端口号有时8001，有时8002。

************************

### 修改Eureka注册的实例主机名和IP显示

* 在8001,8002两个支付模块中做如下修改

  ```yaml
  eureka:
    instance:
      instance-id: payment8002
  ```
  
* 修改完成访问eureka发现service服务名字改变

  * 最好不要改，改了后发现注册不进去了

************************

### 服务发现Discovery

* 作用：获取该微服务模块的信息

* 首先注入DiscoveryClient

  * 注意不是网飞的那个类，不要导错类

  ```java
  @Autowired
  private DiscoveryClient discoveryClient;
  ```

* 然后主启动类添加注解`@EnableDiscoveryClient`

* 获取信息

  ```java
  //服务是对外暴露的微服务名称
  List<String> services = discoveryClient.getServices();
  //实例是一个服务包含的集群里面的各个实例
  List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
  //通过instance可以获得主机号、端口号等信息
  ```
***************
### Eureka保护模式

* 作用：某个微服务在某个时刻不能使用了，不会立刻清理，而是进行保存信息
* 现象：出现很多红色字样

***************

### Ribbon介绍

* 介绍：客户端负载均衡工具

* 负载均衡分类
  * 集中式负载均衡——Nginx(外网流入内网)
  * 本地负载均衡——Ribbon(内网内部分流)
  
* 一句话：本地负载均衡+RestTemplate实现远程调用

* 工作分两步
  *  先选择Eureka Server服务器，找一个比较少的server
  * 根据用户算法策略，从server注册中选取一个提供服务的模块
  
* 导入依赖

  * eureka客户端里面已经整合了ribbon

  ```xml
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
  </dependency>
  ```
********************
### RestTeplate使用

* getForObject()和geForEntiy()区别
  * getForObject()返回对象为响应体中数据转换为的对象，基本可以理解成Json数据
  * getForEntity()返回对象为ResponseEntity对象，包含一些重要信息响应头、响应状态码、响应体等
  
* getForEntity()使用

  ```java
  @RequestMapping("/getForEntity/{id}")
  public CommonResult<Payment> getForEntity(@PathVariable("id") Integer id){
      ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "//payment/payments/", CommonResult.class);
      if (entity.getStatusCode().is2xxSuccessful())
          return entity.getBody();
      else
          return new CommonResult<Payment>(404,"getForEntity查询失败");
  }
  ```
******************
### 核心接口IRule

* 用于确定负载均衡的算法

* ribbon内置有7种

* 替换方法

  * 官方警告，自定义算法不能放在@ComponentScan子包及其目录下，不然会被所有客户端共享，不能特殊化定制

  * 配置入容器，不能放在能扫描的包下

    ```java
    @Configuration
    public class MyRule {
        @Bean("rule")
        public IRule myRule(){
            return new RandomRule();
        }
    }
    ```
  
* 主类添加如下注解

  * name代表这个模块访问的是cloud-payment-service服务模块，后面是算法规则
  
  ```java
  @RibbonClient(name = "cloud-payment-service",configuration = MyRule.class)
  ```
  
* 测试
  
  * 按照顺序打开5个端口，进行调用。实现了随机负载均衡
* 轮询算法原理：求模值为选择服务器的下标值

***************

### OpenFeign

* 概述：是一个声明式的web客户端，让客户端更简便；且集成了ribbon；只需添加一个接口并在接口上添加注解即可

* 新建cloud-consumer-feign-order80模块（一定要新建，不然会和ribbon冲突）

* 导入依赖

  ```XML
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-openfeign</artifactId>
  </dependency>
  ```
  
* 改yml

* 主类添加`@EnableFeignClients`

* 新写service接口

  * 接口方法的参数就是cloud-payment-service的controller对应方法的参数，返回值就是对应的值
  
  ```java
  @Component
  @FeignClient("cloud-payment-service") //代表访问按个eureka服务
  public interface PaymentService {
      @GetMapping("/payments/{id}") 
      CommonResult<Payment> query(@PathVariable Integer id);
      
      @RequestMapping("/payment/payments/insert")
      CommonResult<Payment> insert(Payment payment);
  }
  ```
  
* 新写controller类

  * 核心是insert方法时参数Payment payment是自动填充好的，MVC自动封装好的，此处只能让它自己封装

  ```java
  @RestController
  @RequestMapping("/consumerfeign")
  public class OrderFeignController {
      @Autowired
      private PaymentService service;
  
      @GetMapping("/get/{id}")
      public CommonResult<Payment> query(@PathVariable("id") Integer id){
          return service.query(id);
      }
      
      @RequestMapping("/insert")
      CommonResult<Payment> insert(Payment payment){
          return service.insert(payment);
      }
  }
  ```
* 实际就是封装了一层接口 

*****************

### OpenFeign超时控制

* 默认等待1s就返回错误信息报错、

* 因为openfeign整合了ribbon，因此底层的超时控制由ribbon控制

* 设置读取超时时间和连接超时时间

  ```yaml
  ribbon:
    ReadTimeout: 5000
    ConnectTimeout: 5000
  ```