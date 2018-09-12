package com.liuyang19900520.robotlife.blog.dao.auth;

import com.liuyang19900520.robotlife.blog.domain.user.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @program: robotlife-blog-java
 * @description:
 * @author: LiuYang
 * @create: 2018-09-12 23:29
 **/
public interface AuthenticateDao {

    SysUser findAccount(@Param("userName") String userName);

    Set<String> listRolesByAccount(@Param("userName") String userName);

    Set<String> listPermissionsByAccount(@Param("userName") String userName);
}
