package com.example.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by tym on 2018/8/31 0031.
 */
@Getter
@Setter
@Document(collection = "Users")
public class Users implements Serializable {

    @Id
    private Integer id;
    private String name;
    private int age;

    public Users() {

    }

    public Users(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
