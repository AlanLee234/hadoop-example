package com.alan.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class HDFSOperationTest {
    @Autowired
    private HDFSOperation hdfsOperation;

    @Test
    void upload() {
        /*
        @Value使用的环境要求
            不能作用于静态变量（static）；
            不能作用于常量（final）;
            不能在非注册的类中使用（类需要被注册在spring上下文中，如用@Service,@RestController,@Component等）；
            使用这个类时，只能通过依赖注入的方式，用new的方式是不会自动注入这些配置的。x HDFSOperation hdfsOperation = new HDFSOperation();
         */
        hdfsOperation.upload("src/main/resources/input/file01", "/wordcount/input/");
    }

    @Test
    void getHdfsPath() {
        String hdfsPath = hdfsOperation.getHdfsPath();
        System.out.println(hdfsPath);
    }

    @Test
    void mkdir() {
        hdfsOperation.mkdir("/wordcount/");
    }
}