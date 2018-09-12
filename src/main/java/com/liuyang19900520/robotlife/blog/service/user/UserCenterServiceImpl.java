package com.liuyang19900520.robotlife.blog.service.user;



import com.liuyang19900520.robotlife.blog.dao.user.UserCenterDao;
import com.liuyang19900520.robotlife.blog.domain.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: robotlife-user-service
 * @description:
 * @author: LiuYang
 * @create: 2018-08-23 16:22
 **/
@Service
public class UserCenterServiceImpl implements UserCenterService {

    @Autowired
    UserCenterDao userCenterDao;

    @Override
    public Boolean changeUser(SysUser user) {
        return userCenterDao.changeUser(user) > 0;
    }
}
