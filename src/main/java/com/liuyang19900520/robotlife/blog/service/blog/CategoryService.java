package com.liuyang19900520.robotlife.blog.service.blog;

import com.liuyang19900520.robotlife.blog.domain.blog.Blog;
import com.liuyang19900520.robotlife.blog.domain.blog.Category;

import java.util.List;

/**
 * @program: robotlife-blog-java
 * @description:
 * @author: LiuYang
 * @create: 2018-07-13 14:40
 **/
public interface CategoryService {

    List<Category> listCategories(Category category);

    List<Blog> listBlogTitles(Long categoryId);

    Boolean addCategory(Category category);



}
