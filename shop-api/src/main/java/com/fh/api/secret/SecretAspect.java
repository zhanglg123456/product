package com.fh.api.secret;

import com.fh.api.advice.GlobalException;
import com.fh.api.app.biz.IAppService;
import com.fh.api.common.AccessLimit;
import com.fh.api.common.ResponseEnum;
import com.fh.api.common.ServerResponse;
import com.fh.api.common.WebContext;
import com.fh.api.test.CheckSumBuilder;
import com.fh.api.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @类描述：(请填写这个类的作用)
 * @作者        ：张鹿阁
 * @邮箱        : 200766243@qq.com
 * @创建日期    ：2018年09月29日 19:31
 */
public class SecretAspect {

    @Resource(name = "appService")
    private IAppService appService;


    private long expire = 60 * 1000;
    public Object secretValidate(ProceedingJoinPoint pjp){
        HttpServletRequest request = WebContext.getRequest();

        String appKey = request.getHeader("appKey");
        String currTime = request.getHeader("currTime");
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");

        //判断请求头是否缺失
        if( StringUtils.isEmpty(appKey) ||
            StringUtils.isEmpty(currTime) ||
            StringUtils.isEmpty(sign) ||
            StringUtils.isEmpty(nonce)){

            return ServerResponse.error(ResponseEnum.ERROR_HEADER_LOSE);

        }

        //判断请求是否超时,在过期时间外[重放攻击]

        long longCurrTime = Long.parseLong(currTime)+expire;
        long timeMillis = System.currentTimeMillis();

        if(longCurrTime<timeMillis){
            return ServerResponse.error(ResponseEnum.ERROR_HEADER_TIMEOUT);
        }

        //判断在过期时间内有重新发送了请求[重放攻击]

        int seconds = (int) ((longCurrTime -timeMillis)/1000);
        boolean expire = RedisUtil.setNXAndExpire(nonce, "1", seconds);
        if(!expire){
            return ServerResponse.error(ResponseEnum.ERROR_HEADER_NONCE);
        }


        //判断appKey是否正确
        String appSecret = appService.getAppSecret(appKey);

        if(StringUtils.isEmpty(appSecret)){
            return ServerResponse.error(ResponseEnum.ERROR_HEADER_APPKEY);
        }
        //采用和客户端同样的sha1算法进行对比
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, currTime);

        if(!checkSum.equals(sign)){

            return ServerResponse.error(ResponseEnum.ERROR_HEADER_SIGN);

        }
        //接口限流

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        //判断方法上是否存在我们的自定义注解
        if(method.isAnnotationPresent(AccessLimit.class)){

            AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);

            int interval = accessLimit.interval();
            int nowCount = accessLimit.nowCount();
            //获得请求路径作为redis 存值时key的一部分,避免多个接口方法冲突
            String requestURI = request.getRequestURI();
            //设定调用间隔
            String s = RedisUtil.get(appKey+":"+requestURI);
            if(null!=s){
                return ServerResponse.error(ResponseEnum.ERROR_HEADER_COUNT);
            }
                RedisUtil.setEX(appKey+":"+requestURI,"111",interval);

            long incrExpire = RedisUtil.incrExpire(appKey + ":" + requestURI + "count", 10 * 60);

            if(incrExpire>nowCount){

                return  ServerResponse.error(ResponseEnum.ERROR_HEADER_MINOUT);

            }

        }



        Object result = "";
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new GlobalException(ResponseEnum.ERROR_HEADER_SYSTEM);
        }

        return result;

    }
}
