package cn.UserService.service;


import cn.UserService.POJO.user;
import cn.UserService.mapper.userMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class userService {

    @Autowired
    private userMapper userMapper;

    @GlobalTransactional
    public user get(Integer id) {

        user user = new user();
        user.setId(id);
        System.out.println(user.getId());

        //更新用户信息用于测试seata
        userMapper.update("liuchang", id);
        user user1 = userMapper.get(id);
        log.info("修改过后的user:{}", user1);
        System.out.println(user1);

        return user1;
    }

}
