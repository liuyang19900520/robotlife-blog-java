package com.liuyang19900520.robotlife.blog.service.blog;

import com.liuyang19900520.robotlife.blog.dao.blog.TopicDao;
import com.liuyang19900520.robotlife.blog.domain.blog.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: robotlife-blog-java
 * @description:
 * @author: LiuYang
 * @create: 2018-07-13 14:54
 **/
@Service
public class TopicServiceImpl implements TopicService {


    @Autowired
    TopicDao topicDao;


    @Override
    public List<Topic> listTopics(Topic topic) {
        return topicDao.selectTopics(topic);
    }
}
