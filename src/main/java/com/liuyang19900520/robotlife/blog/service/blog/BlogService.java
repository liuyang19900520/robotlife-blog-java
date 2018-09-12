package com.liuyang19900520.robotlife.blog.service.blog;

import com.liuyang19900520.robotlife.blog.common.pojo.PageBean;
import com.liuyang19900520.robotlife.blog.domain.blog.Blog;

/**
 * @program: robotlife-blog-java
 * @description:
 * @author: LiuYang
 * @create: 2018-07-13 14:40
 **/
public interface BlogService {

    PageBean<Blog> listBlgosByPage(Blog blog, Integer pageNo, Integer rows);

    Blog findBlog(Long key);




}
