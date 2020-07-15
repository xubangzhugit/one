package com.example.controller;

import com.example.util.Qiniu;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by tym on 2018/11/15 0015.
 */
@RestController
public class QiniuController {

    @PostMapping("/up")
    public String upImg(MultipartFile files) throws IOException {

        System.out.println("up:"+files);
        byte[] imgBytes = files.getBytes();
        String imgUrl = Qiniu.upLoadImage(imgBytes);
        System.out.println("imgUrl:"+imgUrl);
        return imgUrl;
    }
}
