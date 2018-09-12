package com.liuyang19900520.robotlife.blog.web.controller.user;



import com.liuyang19900520.robotlife.blog.domain.user.SysUser;
import com.liuyang19900520.robotlife.blog.service.user.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: robotlife-user-service
 * @description:
 * @author: LiuYang
 * @create: 2018-08-23 16:39
 **/
@RestController
@RequestMapping("/users")
public class UserCenterController {


    @Autowired
    UserCenterService userService;

    @PostMapping("/nickname")
    public Object registerEmail(@RequestBody SysUser user) {
        return userService.changeUser(user);
    }


}
