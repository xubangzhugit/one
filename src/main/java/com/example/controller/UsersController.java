package com.example.controller;

import com.example.pojo.NewsDetail;
import com.example.pojo.Users;
import com.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by tym on 2018/8/27 0027.
 */
@RestController
public class UsersController {

    @Autowired
    UsersService service;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     *  =========MongoDB=======
     */
    /**
     * save use before findName
     * @return
     */
    @GetMapping("/saveUsers")
    public Users save() {
        Users user = new Users(5, "涛哥", 21);
        mongoTemplate.save(user);
        //也可以使用Repository插入数据
        //service.save(user);
        return user;
    }

    @GetMapping("/findAllUsers")
    public List<Users> find() {
        List<Users> userList = mongoTemplate.findAll(Users.class);
        return userList;
    }

    /**
     * @param name
     * @return
     */
    @GetMapping("/findByName")
    public Users findByName(@RequestParam("name") String name) {
        //Users user = service.findByName(name);
        //return user;

        Query query=new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Users user =  mongoTemplate.findOne(query, Users.class);

        return user;
    }

    /**
     * 模糊查询+排序分页
     * @return
     */
    @GetMapping("/findByPage")
    public List<Users> findByPage(String name, Integer pageIndex, Integer pageSize){
        Query query = new Query();
        //模糊查询
        Criteria criteria = new Criteria();
        Pattern pattern = Pattern.compile("^.*" + name + ".*$", Pattern.CASE_INSENSITIVE);
        criteria.andOperator(Criteria.where("name").regex(pattern));
        query.addCriteria(criteria);
        //排序
        query.with(new Sort(Sort.Direction.DESC, "age"));
        //分页
        query.skip((pageIndex - 1) * pageSize);                 // 从那条记录开始
        query.limit(pageSize);                                  // 取多少条记录
        long count = mongoTemplate.count(query, Users.class); // 总记录数
        System.out.println("count:"+count);
        return mongoTemplate.find(query, Users.class);
    }

    @GetMapping("/update")
    public Users update(Users user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("name", user.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,Users.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,Users.class);
        return user;
    }

    @GetMapping("/deleteByName")
    public Users deleteByName(@RequestParam("name") String name) {
        Users user = service.findByName(name);
        mongoTemplate.remove(user);
        return user;
    }

    /**
     * 删除对象
     * @param id
     */
    @GetMapping("/deleteById")
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Users.class);
    }

    /**
     *  =========Redis=======
     */
    @GetMapping("/getUser")
    public Users getUser(int id) {
        System.out.println("getUser:");
        return service.getUser(id);
    }

    @GetMapping("/delUser")
    public Users delUser(int id) {
        System.out.println("delUser:");
        return service.delUser(id);
    }

    @GetMapping("/getNews")
    public NewsDetail getNewsDetail(int id) {
        System.out.println("getNewsDetail:");
        return service.findNewsById(id);
    }

    @GetMapping("/findAllNews")
    public List<NewsDetail> findAll() {
        System.out.println("findAll:");
        return service.findAll();
    }
}