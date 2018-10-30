package com.fh.exception;

import com.fh.common.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常统一处理类，在controller.xml中扫描使注解生效
 */
@ControllerAdvice
public class ExceptionAdvice {
    private  static  final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ServerResponse handlerException(Exception ex){


        //第一种方法，占位符的形式
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        LOGGER.error("出现异常啦你！！！！{}",sw.toString());
        //第二种方法，直接输出
      /*  LOGGER.error("-------出现异常！！！-------",ex);*/

      //返回自己封装的异常状态
        return ServerResponse.error();

    }
}
