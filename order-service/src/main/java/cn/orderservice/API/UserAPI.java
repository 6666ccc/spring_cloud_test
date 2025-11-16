package cn.orderservice.API;


import cn.orderservice.Feign_sentinel.UserFallbackFactory;
import cn.orderservice.POJO.user;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "user-service",fallbackFactory = UserFallbackFactory.class)
public interface UserAPI {
    @GetMapping("/get/{id}")
    user getUserById(@PathVariable("id") Integer id);

}
