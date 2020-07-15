package com.example.dao;

import com.example.pojo.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by tym on 2018/8/31 0031.
 */
public interface UsersRepository extends MongoRepository<Users,String> {

    public Users findByName(String name);
}
