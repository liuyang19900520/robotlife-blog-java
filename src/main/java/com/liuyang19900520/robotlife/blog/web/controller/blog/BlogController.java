package com.liuyang19900520.robotlife.blog.web.controller.blog;

import com.liuyang19900520.robotlife.blog.common.util.json.JSON;
import com.liuyang19900520.robotlife.blog.domain.blog.Blog;
import com.liuyang19900520.robotlife.blog.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: robotlife-blog-java
 * @description:
 * @author: LiuYang
 * @create: 2018-07-13 16:08
 **/
@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;


    @PostMapping("/page")
    @JSON(type = Blog.class, include = "blogId,blogTitle,blogSummary,authorId,tags,updateAt")
    public Object listBlogs(Blog blog, Integer pageNo, Integer rows) {
        return blogService.listBlgosByPage(blog, pageNo, rows);
    }

    @GetMapping("/{blogId}")
    @JSON(type = Blog.class, include = "blogId,blogTitle,blogSummary,blogContent,blogHtml,authorId,tags,createAt,categoryId,topicId")
    public Object findBlogs(@PathVariable("blogId") Long blogId) {
        return blogService.findBlog(blogId);
    }




}
