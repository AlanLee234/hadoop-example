package com.alan.service;

import com.alan.hdfs.HdfsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestService.class);

    private final HdfsService hdfsService;

    public TestService(HdfsService hdfsService) {
        LOGGER.info("TestService准备构造注入");
        this.hdfsService = hdfsService;
        LOGGER.info("TestService注入HdfsService成功");
    }

    public void test() throws Exception {
        LOGGER.info("hdfs测试开始");
        hdfsService.test();
        LOGGER.info("hdfs测试完成");
    }
}
