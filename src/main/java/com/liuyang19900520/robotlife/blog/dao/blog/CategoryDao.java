package com.liuyang19900520.robotlife.blog.dao.blog;

import com.liuyang19900520.robotlife.blog.domain.blog.Blog;
import com.liuyang19900520.robotlife.blog.domain.blog.Category;
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
public interface CategoryDao {

    List<Category> selectGategories(Category category);

    List<Blog> selectBolgTitlesByCategoryId(@Param("categoryId") Long cid);

    Integer insertCategory(Category category);

    Integer selectMaxRank();

}
