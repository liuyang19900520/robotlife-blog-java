package com.liuyang19900520.robotlife.blog.domain.blog;

import com.liuyang19900520.robotlife.blog.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: robotlife-blog-java
 * @description:
 * @author: LiuYang
 * @create: 2018-07-13 13:51
 **/
@Data
public class Blog extends BaseEntity implements Serializable {


    Long id;

    Long blogId;
    String blogTitle;
    String blogContent;
    String blogSummary;
    String blogHtml;
    String tags;
    Long categoryId;
    Long authorId;
    Long topicId;

}
