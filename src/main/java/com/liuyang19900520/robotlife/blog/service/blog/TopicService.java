package com.liuyang19900520.robotlife.blog.service.blog;

import com.liuyang19900520.robotlife.blog.domain.blog.Topic;

import java.util.List;

/**
 * @program: robotlife-blog-java
 * @description:
 * @author: LiuYang
 * @create: 2018-07-13 14:40
 **/
public interface TopicService {

    List<Topic> listTopics(Topic topic);


}
