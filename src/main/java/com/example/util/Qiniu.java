package com.example.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * Created by tym on 2018/11/15 0015.
 */
public class Qiniu {
    //根据七牛云的个人中心来填写
    private static final String accessKey = "nceNS3YyKoFGUwmCNVk4u5mR3eB_VnoWnbzxdL8n";
    private static final String secretKey = "tkcUyXOWHvm88nY7scF9VC6bJS3L5Sa5iLWMA88q";
    private static final String bucket = "images";


    /**
     * @param  data
     * @return  外键地址
     */
    public static String upLoadImage(byte[] data){
        //图片的外链地址
        StringBuffer imgUrl = new StringBuffer("http://pi6tfyo5y.bkt.clouddn.com/");

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //生成上传凭证，然后准备上传
        UploadManager uploadManager = new UploadManager(cfg);


        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            //上传文件
            Response response = uploadManager.put(data,key,upToken);

            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
            imgUrl.append(putRet.key);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {

            }
        }
        return imgUrl.toString();
    }
}
