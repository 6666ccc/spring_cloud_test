

# RabbitMQ 使用步骤指南

## 一、引入依赖

在微服务项目中，需要引入 RabbitMQ 相关依赖。以 Spring Boot + Spring AMQP 为例：

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

------

## 二、配置 RabbitMQ

在微服务的 `application.yml` 中配置 RabbitMQ 连接信息：

```
spring:
  rabbitmq:
    host: localhost       # RabbitMQ 服务地址
    port: 5672            # RabbitMQ 默认端口
    username: guest       # 登录用户名
    password: guest       # 登录密码
```

**可选配置**：

- 队列持久化：确保关键消息不会因 RabbitMQ 重启而丢失。
- 消息确认机制：保证消息被成功消费。

------

## 三、创建队列和交换机

RabbitMQ 消息模型由 **队列（Queue）**、**交换机（Exchange）** 和 **路由键（Routing Key）** 构成。

示例：

<img src="https://imgheybox1.max-c.com/web/bbs/2025/11/09/9e56e82a389db1a54aa1731b5522ce2e.png" alt="img" style="zoom:50%;" />



------

## 四、发送消息（Producer）

在服务端发送消息到队列：

<img src="E:\学习记录\rabbitmq.png" style="zoom:50%;" />



```
@Autowired
private RabbitTemplate rabbitTemplate;

public void sendOrderMessage(Order order) {
    rabbitTemplate.convertAndSend(
        "order.exchange",      // 交换机
        "order.routingkey",    // 路由键
        order                  // 消息体
    );
}
```

------

## 五、接收消息（Consumer）

在消费者服务中监听队列消息：

```
@RabbitListener(queues = "order.queue")
public void receiveOrderMessage(Order order) {
    System.out.println("收到订单消息：" + order);
    // 处理订单（扣减库存、发送通知等）
}
```

------

## 六、消息模型选择

<img src="https://imgheybox1.max-c.com/web/bbs/2025/11/10/1e56f571c8e3acff66ed7990fab58fd6.png" alt="img" style="zoom:50%;" />



1. **工作队列（Work Queue）**

   - 每条消息只会被一个消费者处理，适用于任务分发。
   - 只需要讲消费者绑定到同一个队列即可
   - 示例：多个消费者处理用户下单操作，平均分担压力。

2. **发布/订阅（Publish/Subscribe）**

   - 消息广播到所有订阅者，适用于通知或日志场景。
   - 只需要讲消费者分别绑定一个队列即可
   - 示例：订单生成后，所有相关服务（库存、财务、通知）都需要接收到消息。

   假设: 用户 A 下单三次：买了 B、C、D 消费者数量：C1,C2,C3 

   1.使用workqueue则是: C1收到A买了B;     C2收到A买了C;      C3收到A买了D 

   2.使用发布和订阅，广播则是 C1收到A买了 B、C、D;    C2收到A买了 B、C、D;   C3收到A买了 B、C、D

