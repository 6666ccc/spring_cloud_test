# Spring Cloud 微服务项目

这是一个包含两个微服务的Spring Cloud项目：
1. `user-service` - 处理用户管理
2. `order-service` - 处理订单管理

## 项目结构

```
parent/
├── user-service/
│   ├── src/main/java/cn/UserService/
│   │   ├── POJO/
│   │   │   └── user.java
│   │   ├── controller/
│   │   │   └── userController.java
│   │   ├── mapper/
│   │   │   └── userMapper.java
│   │   ├── service/
│   │   │   └── userService.java
│   │   └── UserServiceApplication.java
│   └── pom.xml
├── order-service/
│   ├── src/main/java/cn/orderservice/
│   │   ├── API/
│   │   │   └── UserAPI.java
│   │   ├── Feign_sentinel/
│   │   │   └── UserFallbackFactory.java
│   │   ├── POJO/
│   │   │   ├── order.java
│   │   │   └── user.java
│   │   ├── Util/RabbitMq/
│   │   │   └── RabbitConfig.java
│   │   ├── controller/
│   │   │   └── ordercontroller.java
│   │   ├── mapper/
│   │   │   └── ordermapper.java
│   │   ├── service/
│   │   │   └── orderservice.java
│   │   └── OrderServiceApplication.java
│   └── pom.xml
└── pom.xml
```



## 项目说明

这个项目是我在学习 Spring Cloud 过程中的练习项目，同时也包含一些写好的指导文档。  
项目由两个微服务模块组成：  

1. **用户服务（user-service）**：负责用户的注册、登录、信息查询和管理。  
   - 技术点：MyBatis-Plus 操作数据库、REST API 接口设计、基本的服务层逻辑封装。  
   - 学习心得：通过该模块熟悉了 Spring Boot 与 MyBatis-Plus 的集成，以及如何设计简单的 CRUD 接口和测试数据流。

2. **订单服务（order-service）**：负责订单创建、查询及与用户服务的数据交互。  
   - 技术点：Feign 远程调用用户服务、Sentinel 限流与熔断、RabbitMQ 消息队列处理、分布式事务初步实践。  
   - 学习心得：通过该模块学习了微服务间通信方式、异常处理策略以及消息队列在异步处理中的应用，理解了服务解耦的重要性。

整个项目的核心目标是记录学习过程和实践经验，覆盖了微服务架构的基础知识：  
- 服务注册与发现（Nacos）  
- 服务间远程调用与容错（Feign + Sentinel）  
- 消息队列异步处理（RabbitMQ）  
- 数据访问层设计与操作（MyBatis-Plus + MySQL）  
- 项目结构规划与模块划分  

通过这个项目，我能够更好地理解 Spring Cloud 技术栈的使用方法、各模块的职责划分，以及微服务开发中常见问题的解决方案。  
整个项目**纯粹作为学习参考和个人记录**，便于回顾学习过程、总结经验，并为以后的微服务项目开发提供实践参考和模板。



注意!!!其他.md为我的笔记和总结.