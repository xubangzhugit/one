package com.example.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tym on 2018/5/6 0006.
 */
@Getter
@Setter
public class NewsComment implements Serializable {

    private long cid;
    private long newsId;
    private String content;
    private String cauthor;
    private Date ccreatedate;

}