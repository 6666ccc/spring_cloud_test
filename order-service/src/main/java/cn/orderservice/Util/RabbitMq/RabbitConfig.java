package cn.orderservice.Util.RabbitMq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // 1. 定义队列
    @Bean
    public Queue orderQueue() {
        return new Queue("order-queue", true); // 第二个参数表示是否持久化
    }

    // 2. 定义交换机
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange("order-exchange", true, false);
    }

    // 3. 队列与交换机绑定，并指定 routing key
    @Bean
    public Binding orderBinding(Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with("order.get");
    }
}
