package cn.orderservice.Feign_sentinel;

import cn.orderservice.API.UserAPI;
import cn.orderservice.POJO.user;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFallbackFactory implements FallbackFactory<UserAPI> {
    @Override
    public UserAPI create(Throwable cause) {
        return new UserAPI() {
            @Override
            public user getUserById(Integer id) {
                log.error("这是请求被sentinel拒绝后的消息{}", String.valueOf(cause));
                return new user();
            }
        };
    }
}
