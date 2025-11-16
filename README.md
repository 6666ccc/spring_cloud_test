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

## 如何将项目上传到GitHub

### 准备工作
1. 在电脑上安装Git
2. 注册一个GitHub账户
3. 确保本地有这个项目文件

### 详细步骤

#### 1. 初始化本地仓库
在项目的根目录（`parent/`）下打开终端/命令提示符并运行：

```bash
git init
```

#### 2. 添加所有文件到暂存区
```bash
git add .
```

#### 3. 提交文件
```bash
git commit -m "Initial commit"
```

#### 4. 在GitHub上创建新仓库
1. 访问 [GitHub](https://github.com/)
2. 点击右上角的"+"图标
3. 选择"New repository"
4. 给你的仓库起个名字（例如："spring-cloud-project"）
5. 可以添加描述信息
6. 选择公开(Public)或私有(Private)
7. 不要初始化README文件（我们将推送现有的README）
8. 点击"Create repository"

#### 5. 将本地仓库连接到GitHub
从GitHub复制仓库URL（格式类似：`https://github.com/用户名/仓库名.git`）

在终端中运行：
```bash
git remote add origin https://github.com/你的用户名/仓库名.git
```

#### 6. 推送到GitHub
```bash
git push -u origin master
```

## 后续更新

当你对项目做了修改后，可以按以下步骤推送到GitHub：

```bash
git add .
git commit -m "描述你的更改"
git push origin master
```