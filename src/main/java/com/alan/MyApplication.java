package com.alan;

//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication() // exclude = {DruidDataSourceAutoConfigure.class}
@ComponentScan("com.alan.*")
public class MyApplication {
    public static void main(String[] args) {
        System.out.println("start");
        SpringApplication.run(MyApplication.class, args);
    }
}
