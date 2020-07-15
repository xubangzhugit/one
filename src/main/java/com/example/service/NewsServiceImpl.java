package com.example.service;

import com.example.dao.NewsCommentMapper;
import com.example.dao.NewsDetailMapper;
import com.example.pojo.NewsComment;
import com.example.pojo.NewsDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by tym on 2018/5/6 0006.
 */
@Service
@Transactional
//@AutoConfigureAfter({NewsDetailMapper.class, NewsCommentMapper.class})
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDetailMapper ndMapper;
    @Autowired
    private NewsCommentMapper ncMapper;

    @Override
    public List<NewsDetail> findAllNews() {
        return ndMapper.findAllNews();
    }

    @Override
    public int deleteNewsDetail(long id) {
        ncMapper.deleteByNewsId(id);
        return ndMapper.deleteById(id);
    }

    @Override
    public int saveNewsComment(NewsComment nc) {
        return ncMapper.insert(nc);
    }

    @Override
    public List<Map<String, Object>> selectAll() {
        return ncMapper.selectAll();
    }


}
