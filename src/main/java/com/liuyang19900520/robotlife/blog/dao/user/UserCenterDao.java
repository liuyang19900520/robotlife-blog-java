package com.liuyang19900520.robotlife.blog.dao.user;


import com.liuyang19900520.robotlife.blog.domain.user.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCenterDao {

    Integer changeUser(SysUser user);


}
