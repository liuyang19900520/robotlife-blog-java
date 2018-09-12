package com.liuyang19900520.robotlife.blog.service.auth;

import com.liuyang19900520.robotlife.blog.domain.user.SysUser;

import java.util.Set;

/**
 * Created by liuyang on 2018/3/16
 *
 * @author liuya
 */
public interface AuthenticateService {


    /**
     * 查找当前用户
     *
     * @param userName
     * @return 登录用户
     */
    SysUser findUserByAccount(String userName);

    /**
     * 获得当前用户角色
     *
     * @param userName
     * @return 登录用户
     */
    Set<String> listRolesByAccount(String userName);

    /**
     * 获得当前用户权限
     *
     * @param userName
     * @return 登录用户
     */
    Set<String> listPermissionsByAccount(String userName);


}
