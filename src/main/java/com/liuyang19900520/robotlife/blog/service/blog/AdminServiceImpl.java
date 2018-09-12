package com.liuyang19900520.robotlife.blog.service.blog;

import com.liuyang19900520.robotlife.blog.common.util.LIdGenerator;
import com.liuyang19900520.robotlife.blog.dao.blog.BlogDao;
import com.liuyang19900520.robotlife.blog.domain.blog.Blog;
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
public class AdminServiceImpl implements AdminService {

    @Autowired
    BlogDao blogDao;

    /**
     * Add blog
     *
     * @param blog
     * @return isInserted
     */
    @Override
    public Boolean addBlog(Blog blog) {

        if (blog.getBlogId() == null) {
            blog.setBlogId(new LIdGenerator().nextId());
        }
        blog.setAuthorId(Long.parseLong("19900520"));
        String summary = "";
        if (blog.getBlogContent().length() > 150) {
            summary = blog.getBlogContent().replaceAll("#", "").replaceAll("\n", "").substring(0, 150);
        } else {
            summary = blog.getBlogContent().replaceAll("#", "").replaceAll("\n", "");
        }

        blog.setBlogSummary(summary);
        Integer inInserted = blogDao.insertBlog(blog);

        blogDao.deleteTempBlog(blog.getBlogId());


        return inInserted > 0;
    }

    @Override
    public Boolean addTempBlog(Blog blog) {
        if (blog.getBlogId() == null) {
            blog.setBlogId(new LIdGenerator().nextId());
        }
        blog.setAuthorId(Long.parseLong("19900520"));
        Integer inInserted = blogDao.insertTempBlog(blog);
        return inInserted > 0;
    }

    @Override
    public List<Blog> listTempBlogs() {
        return blogDao.selectTempBlogs();
    }

    @Override
    public Blog findTempBlog(Long key) {
        return blogDao.selectTempBlogByKey(key);
    }


}
