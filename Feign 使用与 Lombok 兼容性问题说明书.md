# Feign 使用与 Lombok 兼容性问题说明书

本文记录在 Spring Cloud 项目中使用 OpenFeign 进行服务调用时的基本步骤、遇到的问题、排查过程与最终解决方案。

------

## 一、Feign 基础使用流程

在 Spring Cloud 环境中使用 Feign 进行远程调用，需要完成以下步骤：

### 1. 引入依赖

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

### 2. 启用 Feign 功能

在主启动类上添加注解：

```
@EnableFeignClients
```

### 3. 定义 Feign 客户端接口

通过 `@FeignClient` 指定要调用的服务名（与 Nacos 注册一致）：

```
@FeignClient(value = "user-service")
public interface UserClient {
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") Long id);
}
```

### 4. 在业务层直接调用

Feign 会自动生成远程调用代理，直接注入并使用即可。

------

## 二、Lombok 未生效问题

### 现象

在使用 Spring Boot **3.2.12** 的模块中，Lombok 明明已经依赖，却在编译时报错。
 报错信息中显示实体类无法找到 getter/setter。
 手动编写 getter/setter 后功能正常，由此排除实体类本身问题。

### 初步判断

Lombok 插件正常，依赖存在，但 **未实际生效**。

------

## 三、原因排查

为进一步验证，我重新创建了一个全新模块：

- Spring Boot 3.5.7
- 引入 Lombok、Web、MySQL
- 测试正常运行

两个模块之间唯一差异是 **Spring Boot 版本不同**。

由此可以确认：
 **Spring Boot 3.2.12 与当前 Lombok 自动版本之间存在兼容性问题。**

这种现象在 Boot 3.x 早期版本中偶尔会出现，尤其是在 Maven 未显式指定 Lombok 或 Maven Compiler 的版本时。