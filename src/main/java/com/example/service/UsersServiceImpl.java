package com.example.service;

import com.example.dao.NewsDetailMapper;
import com.example.dao.UsersRepository;
import com.example.pojo.NewsDetail;
import com.example.pojo.Users;
import com.example.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tym on 2018/8/27 0027.
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    NewsDetailMapper newsDetailMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private UsersRepository usersRepository;

    /**
     *  =========MongoDB=======
     */
    @Override
    public void save(Users user) {
        usersRepository.save(user);
    }

    @Override
    public Users findByName(String name) {
        return usersRepository.findByName(name);
    }

    /**
     *  =========Redis=======
     */
    @Override
    //@CachePut(value="user", key="#id", condition="#id%2==1", unless="#result==null")
    @Cacheable(value="user", key="#id", condition="#id%2==0", unless="#result==null")
    public Users getUser(int id) {
        System.out.println("UsersService getUser:"+id);
        Users user = new Users();
        user.setId(id);
        user.setName("灵哥");
        return user;
    }

    @Override
    @CacheEvict(value="user", key="#id", condition="#id%2==0")
    public Users delUser(int id) {
        System.out.println("UsersService del:"+id);
        Users user = new Users();
        user.setId(id);
        return user;
    }

    @Override
    @Cacheable(value="news", key="#id")
    public NewsDetail findNewsById(int id){
        System.out.println("findNewsById:"+id);
        NewsDetail nd  = newsDetailMapper.findNewsById(id);
        return nd;
    }

    @Override
    public List<NewsDetail> findAll(){
        List<NewsDetail> ls = null;

        if(redisUtil.exists("ls")){
            System.out.println("exists:");
            ls = (List<NewsDetail>)redisUtil.get("ls");
            System.out.println("ls:");
            for (NewsDetail n : ls) {
                System.out.println(n.getId()+"\t"+n.getTitle());
            }
        }else{
            System.out.println("not exists:");
            //从数据库取并保存到redis
            ls = newsDetailMapper.findAllNews();
            redisUtil.set("ls", ls, 30L);
        }
        return ls;
    }
}