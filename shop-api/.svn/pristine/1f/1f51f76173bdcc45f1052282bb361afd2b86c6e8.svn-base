package com.fh.api.list.biz;


import com.fh.api.common.ServerResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-common.xml"})
public class TestProtalService {

    @Resource(name = "protalService")
    private ProtalService protalService;

    @Test
    public void  testFindList(){

        ServerResponse response = protalService.findList();
        System.out.println(response);

    }
}
