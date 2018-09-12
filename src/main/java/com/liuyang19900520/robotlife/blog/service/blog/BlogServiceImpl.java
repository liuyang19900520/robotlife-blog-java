package com.liuyang19900520.robotlife.blog.service.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuyang19900520.robotlife.blog.common.pojo.PageBean;
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
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Override
    public PageBean<Blog> listBlgosByPage(Blog blog, Integer pageNo, Integer rows) {

        PageHelper.startPage(pageNo, rows);
        List<Blog> list = blogDao.selectBlogsByPage(blog);

        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        PageBean<Blog> result = new PageBean<>();
        result.setData(pageInfo.getList());
        result.setPagesTotal(pageInfo.getPages());

        return result;
    }



    @Override
    public Blog findBlog(Long key) {
        return blogDao.selectBlogByKey(key);
    }


}
