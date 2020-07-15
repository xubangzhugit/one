package com.example.dao;

import com.example.pojo.NewsDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by tym on 2018/5/6 0006.
 */
public interface NewsDetailMapper {

    public List<NewsDetail> findAllNews();

    public NewsDetail findNewsById(long id);

    public int deleteById(@Param("id") long id);
}
