package cn.UserService.controller;


import cn.UserService.POJO.user;
import cn.UserService.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class userController {
    @Autowired
    private userService userService;

    @GetMapping("/get/{id}")
    public user getUser(@PathVariable Integer id) throws InterruptedException {
        if (id == 2){
            Thread.sleep(600);
        }
        return userService.get(id);
    }


}
