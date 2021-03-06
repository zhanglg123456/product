package com.fh.api.list.controller;


import com.fh.api.common.AccessLimit;
import com.fh.api.common.ServerResponse;
import com.fh.api.list.biz.ProtalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/list")
public class ListController {

    @Resource(name = "protalService")
    private ProtalService protalService;



    @AccessLimit(interval = 10, nowCount = 5)
    @RequestMapping(value = "index",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @CrossOrigin("*")
    public Object findList(String  callback){
        ServerResponse list = protalService.findList();
        //判断callback是否为空,空的话就是普通请求,不为空的话就是jsonp请求,进行相应的返回

        if(StringUtils.isBlank(callback)){


            return list;
        }else {
            //使用spring 4.0版本的工具类来处理针对jsonp跨域的回调函数返回
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

            mappingJacksonValue.setJsonpFunction(callback);


            return  mappingJacksonValue;

        }

    }



}
