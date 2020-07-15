package com.example.service;

import com.example.pojo.NewsDetail;
import com.example.pojo.Users;

import java.util.List;

/**
 * Created by tym on 2018/8/31 0031.
 */
public interface UsersService {
    void save(Users user);

    Users findByName(String name);

    Users getUser(int id);

    Users delUser(int id);

    NewsDetail findNewsById(int id);

    List<NewsDetail> findAll();
}
