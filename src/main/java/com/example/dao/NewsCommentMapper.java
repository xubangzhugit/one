package com.example.dao;


import com.example.pojo.NewsComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by tym on 2018/5/6 0006.
 */
public interface NewsCommentMapper {

    public int insert(NewsComment nc);

    public int deleteByNewsId(@Param("newsId") long newsId);

    public List<Map<String,Object>> selectAll();
}
