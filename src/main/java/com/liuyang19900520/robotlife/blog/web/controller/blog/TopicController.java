package com.liuyang19900520.robotlife.blog.web.controller.blog;

import com.liuyang19900520.robotlife.blog.common.util.json.JSON;
import com.liuyang19900520.robotlife.blog.domain.blog.Blog;
import com.liuyang19900520.robotlife.blog.domain.blog.Topic;
import com.liuyang19900520.robotlife.blog.service.blog.BlogService;
import com.liuyang19900520.robotlife.blog.service.blog.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: robotlife-blog-java
 * @description:
 * @author: LiuYang
 * @create: 2018-07-13 16:08
 **/
@Controller
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    BlogService blogService;

    @GetMapping("")
    @JSON(type = Topic.class, include = "topicId,topicContent,topicRank")
    public Object listCategories(Topic topic) {
        return topicService.listTopics(topic);
    }


    @PostMapping("/{topicId}")
    @JSON(type = Topic.class, include = "topicId,topicContent,topicRank")
    public Object listBlogInTopic(@PathVariable Long topicId, Integer pageNo, Integer rows) {
        Blog query = new Blog();
        query.setTopicId(topicId);
        return blogService.listBlgosByPage(query, pageNo, rows);
    }


}
