package com.liuyang19900520.robotlife.blog.dao.blog;

import com.liuyang19900520.robotlife.blog.domain.blog.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface BlogDao {

    List<Blog> selectBlogsByPage(Blog blog);

    Blog selectBlogByKey(@Param("blogId") Long key);

    Integer insertBlog(Blog blog);

    Integer insertTempBlog(Blog blog);

    List<Blog> selectTempBlogs();

    Blog selectTempBlogByKey(@Param("blogId") Long key);

    Integer deleteTempBlog(@Param("blogId") Long key);
}
