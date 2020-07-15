package com.example.service;


import com.example.pojo.NewsComment;
import com.example.pojo.NewsDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by tym on 2018/5/6 0006.
 */
public interface NewsService {

    public List<NewsDetail> findAllNews();

    public int deleteNewsDetail(long id);

    public int saveNewsComment(NewsComment nc);

    public List<Map<String,Object>> selectAll();
}
