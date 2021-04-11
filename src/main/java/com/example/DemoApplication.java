package com.example;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//@EnableScheduling
@EnableCaching//启用redis的自动存取机制
@MapperScan(basePackages="com.example.dao")
@EnableTransactionManagement
@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) throws Exception
	{

		SpringApplication.run(DemoApplication.class,args);
		/*SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBannerMode(Banner.Mode.OFF);// 关闭启动Banner
		app.run(args);   xubangzhu     */
	}
}
