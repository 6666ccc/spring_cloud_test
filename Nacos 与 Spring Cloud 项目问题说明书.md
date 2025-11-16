# Nacos 与 Spring Cloud 项目问题说明书

本文记录在使用 Spring Cloud、Spring Cloud Alibaba 与 Nacos 构建微服务项目的过程中，出现的问题、原因分析及解决方案，以便后续参考。

## **一、服务无法按服务名访问的问题**

### 现象

客户端order-server发出的请求为

`http://localhost:8080/user/`

而不是根据服务注册名自动解析的：

`http://user-service/user/`

### 原因

1. 负载均衡未正确生效，客户端未根据服务名进行调用。

### 解决方案

1. 使用服务名访问接口，确保使用的地址为 `http://{服务名}/{接口路径}`。而不是`http://localhost/user`



## 二、Spring Cloud / Spring Cloud Alibaba / Spring Boot 版本不兼容问题

### 现象

- 负载均衡策略无法正常工作。
- 旧教程使用的 Ribbon 已被弃用，无法在新版环境生效。
- 手动导入依赖出现版本冲突，导致 MyBatis 查询失败。
- 子项目因为 parent POM 修改不当而无法被识别。
- Spring Boot 低版本、高版本均无法与当前 Cloud 版本兼容。

### 版本要求总结

经验证，当前可正常运行的版本组合如下：

- **Spring Cloud：2023.0.0**
- **Spring Cloud Alibaba：2023.0.0.0-RC1**
- **Spring Boot：3.2.12（必须在 3.2.x 范围内）**
- **Nacos：3.x**

在此版本组合下：
 Ribbon 不再使用，需引入 Spring Cloud 官方的 LoadBalancer。

### 解决方案

1. 重建 Cloud 项目，避免父级 POM 结构错误导致子项目不可识别。
2. 全量对齐以上版本，确保三方依赖链一致。
3. 引入如下依赖启用服务注册与配置中心：

`spring-cloud-starter-alibaba-nacos-discovery`
`spring-cloud-starter-alibaba-nacos-config`

​			4.引入新版负载均衡依赖：

`spring-cloud-starter-loadbalancer`

​			5.完成正确的 yml 配置，并在启动类添加 `@EnableDiscoveryClient`。

## 三、Nacos 基础配置示例

以下为经过验证可正常运行的配置：

`server:`
  `port: 8081`

`spring:`
  `application:`
    `name: user-service`
  `cloud:`
    `nacos:`
      `discovery:`
        `server-addr: localhost:8848`
      `config:`
        `server-addr: localhost:8848`
        `file-extension: yaml`
        `group: DEFAULT_GROUP`
        `namespace: public`