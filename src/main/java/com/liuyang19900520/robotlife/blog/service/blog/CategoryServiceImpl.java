package com.liuyang19900520.robotlife.blog.service.blog;

import com.google.common.collect.Lists;
import com.liuyang19900520.robotlife.blog.common.util.LIdGenerator;
import com.liuyang19900520.robotlife.blog.dao.blog.CategoryDao;
import com.liuyang19900520.robotlife.blog.domain.blog.Blog;
import com.liuyang19900520.robotlife.blog.domain.blog.Category;
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
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Category> listCategories(Category category) {
        return categoryDao.selectGategories(category);
    }

    @Override
    public List<Blog> listBlogTitles(Long categoryId) {
        return categoryDao.selectBolgTitlesByCategoryId(categoryId);
    }

    @Override
    public Boolean addCategory(Category category) {

        if (category.getCategoryId() == null) {
            category.setCategoryId(new LIdGenerator().nextId());
        }
        category.setCreateBy(Long.parseLong("19900520"));
        category.setUpdateBy(Long.parseLong("19900520"));
        category.setCategoryRank(categoryDao.selectMaxRank());

        Lists.newArrayList();
        return categoryDao.insertCategory(category) > 0;
    }


}


