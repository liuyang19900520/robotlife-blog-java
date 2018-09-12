package com.liuyang19900520.robotlife.blog.dao.blog;

import com.liuyang19900520.robotlife.blog.domain.blog.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: robotlife-blog-java
 * @description:
 * @author: LiuYang
 * @create: 2018-07-13 15:10
 **/
@Repository
@Mapper
public interface TopicDao {

    List<Topic> selectTopics(Topic topic);


}
