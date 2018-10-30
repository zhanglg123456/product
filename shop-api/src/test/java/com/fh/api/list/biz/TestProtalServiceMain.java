package com.fh.api.list.biz;


import com.fh.api.common.ServerResponse;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProtalServiceMain {

    public static void main(String[] args) {
        //加载配置文件，可加载多个，用，隔开

        String [] locations = {"classpath:spring/spring-common.xml"};
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext(locations);

        //获取service
        ProtalService protalService = (ProtalService) xmlApplicationContext.getBean("protalService");

        //调用方法
        ServerResponse list = protalService.findList();
        System.out.println(list);

    }
}
