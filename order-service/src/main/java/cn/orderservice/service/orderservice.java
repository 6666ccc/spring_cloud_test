package cn.orderservice.service;


import cn.orderservice.API.UserAPI;
import cn.orderservice.POJO.order;
import cn.orderservice.POJO.user;
import cn.orderservice.mapper.ordermapper;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class orderservice {

    @Autowired
    private ordermapper ordermapper;

    // feign
    @Autowired
    private UserAPI userAPI;

    // rabbitMq
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GlobalTransactional// 开启全局事务
    public order get(Integer id) throws InterruptedException {

        order order = ordermapper.get(id);
        // 判断库存是否足够
        if (order.getNum() < 3) {
            throw new RuntimeException("库存不足，无法购买");
        }

        // 使用rabbitmq发送消息
        rabbitTemplate.convertAndSend("order-exchange", "order.get"," hello");
        ordermapper.buy(id, 3);
        log.info("order:{}", order);
        user userById = userAPI.getUserById(order.getUser_id());
        order.setUser(userById);
        return order;
    }

    @SentinelResource(value = "get2")
    public String get2(){
        return "get2";
    }

    public String handleException(Integer id, Throwable e) {
        return "服务降级";
    }
}