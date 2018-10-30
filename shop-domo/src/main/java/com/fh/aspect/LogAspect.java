package com.fh.aspect;

import com.fh.biz.log.ISysLogService;
import com.fh.common.ServerResponse;
import com.fh.common.SystemConst;
import com.fh.common.WebContext;
import com.fh.po.admin.User;
import com.fh.po.log.SystemLog;
import com.fh.util.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 日志切面类
 */
public class LogAspect {

    private  static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);


    @Resource(name = "sysLogService")
    private ISysLogService sysLogService;


    public  Object doLog(ProceedingJoinPoint point){

        //获得目标类名
        String className = point.getTarget().getClass().getName();
        //获得目标方法名
        String methodName = point.getSignature().getName();

        //定义一个变量
        Object result = null;
        String message = "";
        long start;
        long end;

        Date exceptionDate = null;
        //获得request对象
        HttpServletRequest request = WebContext.getRequest();
        String ipAddress = IpUtil.getIpAddress(request);
        User user = (User) request.getSession().getAttribute(SystemConst.USER_MASSAGE);

        try {
            //执行真实的方法返回结果
            if(null!=user){
                LOGGER.info("用户{}开始执行{}类的{}方法,IP地址是:{}",user.getRelname(), className, methodName, ipAddress);

            }else {
                LOGGER.info("开始执行{}类的{}方法", className, methodName);
            }
            start = System.currentTimeMillis();
            result =  point.proceed();
            end = System.currentTimeMillis();
            //判断user是否为空不为空在进行记录
            if(null!=user){
                LOGGER.info("用户{}执行{}类的{}方法结束，共耗费了{}毫秒,IP地址是:{}",user.getRelname(),className,methodName,end-start, ipAddress);
            }else {
                LOGGER.info("执行{}类的{}方法结束，共耗费了{}毫秒",className,methodName,end-start);

            }

        } catch (Throwable throwable) {

            LOGGER.error("{}类的{}方法出现异常,异常信息是{}",className,methodName,throwable.getMessage());
            //日志统一处理，错误状态返回前台
            message = throwable.getMessage();
            exceptionDate = new Date();
            return ServerResponse.error();
        }

        //将日志记录到数据库
        if(null!=user){
            String info = "执行了" + className + "类的" + methodName + "方法";

            logInfoToDB(user.getUsername(),info,ipAddress,new Date(),end-start,SystemConst.LOG_STATUS_OK,message,exceptionDate);

        }



        return  result;
    }


    private void logInfoToDB(String username, String info, String ipAddr, Date createTime, long useTime, Integer status,String exceptionInfo,Date exceptionTime){

        SystemLog systemLog = new SystemLog();
        systemLog.setUsername(username);
        systemLog.setInfo(info);
        systemLog.setStatus(status);
        systemLog.setIpAddr(ipAddr);
        systemLog.setCreateTime(createTime);
        systemLog.setUseTime(useTime);
        systemLog.setExceptionInfo(exceptionInfo);
        systemLog.setExceptionTime(exceptionTime);
        sysLogService.addLogToDB(systemLog);


    }

}
