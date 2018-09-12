package com.liuyang19900520.robotlife.blog.web.controller.auth;



import com.liuyang19900520.robotlife.blog.common.pojo.Messages;
import com.liuyang19900520.robotlife.blog.common.pojo.ResultVo;
import com.liuyang19900520.robotlife.blog.domain.user.SysUser;
import com.liuyang19900520.robotlife.blog.service.auth.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyang on 2018/3/16
 *
 * @author liuya
 */
@Slf4j

@RestController
@RequestMapping("/auth")
public class AuthenticateController {


    @Autowired
    AuthenticateService authenticateService;


    /**
     * 登录
     *
     * @param loginUser
     * @return
     */

    @PostMapping("/signin")
    public Object signin(@RequestBody SysUser loginUser) {

        SysUser user = authenticateService.findUserByAccount(loginUser.getUserName());

        return ResultVo.success(Messages.OK, user);

    }



}
