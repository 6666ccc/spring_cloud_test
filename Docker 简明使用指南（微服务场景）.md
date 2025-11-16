# Docker 简明使用指南（微服务场景）

## 一、Docker 简介

Docker 是一个轻量级容器化平台，用于将应用及其依赖打包到一个**标准化、可移植的容器**中。它可以在任意环境中快速运行，并且隔离性好，适合微服务部署。

### Docker 与虚拟机的区别

| 特性     | Docker | 虚拟机 |
| -------- | ------ | ------ |
| 启动速度 | 秒级   | 分钟级 |
| 资源开销 | 少     | 高     |
| 隔离     | 进程级 | 内核级 |
| 镜像大小 | 小     | 大     |

------

## 二、Docker 核心概念

1. **镜像（Image）**
   - 应用及其依赖的打包文件，类似“快照”。
   - 示例：`openjdk:17`, `mysql:8.0`。
2. **容器（Container）**
   - 镜像的运行实例，可以启动、停止、删除。
   - 一个镜像可以运行多个容器。
3. **仓库（Registry）**
   - 存储镜像的地方，如 Docker Hub。
   - 可以拉取公共镜像或上传自己的镜像。

------

## 三、Docker 常用命令

| 操作             | 命令示例                                                     | 说明                         |
| ---------------- | ------------------------------------------------------------ | ---------------------------- |
| 查看 Docker 版本 | `docker --version`                                           | 验证安装是否成功             |
| 拉取镜像         | `docker pull mysql:8.0`                                      | 从仓库下载镜像               |
| 查看镜像         | `docker images`                                              | 列出本地镜像                 |
| 启动容器         | `docker run -d --name my-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0` | `-d` 后台运行，`-p` 端口映射 |
| 查看运行中容器   | `docker ps`                                                  | 列出正在运行的容器           |
| 停止容器         | `docker stop my-mysql`                                       | 停止容器                     |
| 删除容器         | `docker rm my-mysql`                                         | 删除停止的容器               |
| 删除镜像         | `docker rmi mysql:8.0`                                       | 删除镜像                     |

------

## 四、Dockerfile 基本写法

Dockerfile 用于构建自定义镜像。示例：

```
# 基础镜像
FROM openjdk:17-jdk-slim

# 工作目录
WORKDIR /app

# 复制项目 jar 包
COPY target/my-service.jar .

# 暴露端口
EXPOSE 8080

# 启动命令
CMD ["java", "-jar", "my-service.jar"]
```

构建镜像：

```
docker build -t my-service:1.0 .
```

运行容器：

```
docker run -d -p 8080:8080 --name my-service-container my-service:1.0
```

------

## 五、微服务场景下的 Docker 使用

1. **数据库容器**
   - 例如 MySQL、Redis，快速部署环境，无需在本地安装。
2. **服务容器**
   - 将 Spring Boot 微服务打包成镜像，启动多个副本，实现负载均衡。
3. **消息队列容器**
   - RabbitMQ、Kafka 等消息中间件也可以用 Docker 启动，方便测试。
4. **集群部署**
   - 可以用 Docker Compose 或 Kubernetes 将多个微服务容器编排成完整环境。

------

## 六、Docker Compose（可选）

用于管理多个容器的编排，示例：

```
version: '3'
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: 123456
    ports:
      - "3306:3306"

  user-service:
    build: ./user-service
    ports:
      - "8081:8080"
    depends_on:
      - mysql

  order-service:
    build: ./order-service
    ports:
      - "8082:8080"
    depends_on:
      - mysql
```