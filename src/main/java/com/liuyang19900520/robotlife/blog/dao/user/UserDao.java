package com.liuyang19900520.robotlife.blog.dao.user;



import com.liuyang19900520.robotlife.blog.domain.user.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserDao {

    Integer signUp(SysUser user);

    SysUser checkUser(SysUser user);

    Integer activeUser(@Param("code") String code);

}
