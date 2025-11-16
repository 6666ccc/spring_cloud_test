package cn.orderservice.controller;

import cn.orderservice.POJO.order;
import cn.orderservice.service.orderservice;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class ordercontroller {
    @Autowired
    private orderservice orderservice;

    @SentinelResource(value = "get", blockHandler = "handleException")
    @GetMapping("/order/{id}")
    public order get(@PathVariable Integer id) throws InterruptedException {
        return orderservice.get(id);
    }
    @GetMapping("/order")
    public String get2(){
        return orderservice.get2();
    }

}
