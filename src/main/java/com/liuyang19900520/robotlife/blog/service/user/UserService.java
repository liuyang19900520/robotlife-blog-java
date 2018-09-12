package com.liuyang19900520.robotlife.blog.service.user;


import com.liuyang19900520.robotlife.blog.domain.user.SysUser;

import java.util.Map;
import java.util.Set;

public interface UserService {

    Map<String, Object> signUp(String type, SysUser user);

    Boolean active(String code);


}
