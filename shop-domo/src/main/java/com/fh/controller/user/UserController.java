package com.fh.controller.user;

import com.fh.biz.user.IUserService;
import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.common.SystemConst;
import com.fh.po.admin.User;
import com.fh.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class UserController {

    @Resource(name = "iUserService")
    private IUserService iUserService;


    @RequestMapping("/loginOut")
    @ResponseBody
    public ServerResponse loginOut(HttpServletRequest request){
        //移除session
        request.getSession().removeAttribute(SystemConst.USER_MASSAGE);
        //让所有session失效
        request.getSession().invalidate();



        return  ServerResponse.success();

    }



    @RequestMapping("/login")
    @ResponseBody
    public ServerResponse login(User user, HttpServletRequest request){
        //用户名
        String username = user.getUsername();
        //密码
        String userpass = user.getUserpass();
        //验证码
        String verifyCode = user.getVerifyCode();
        //判断用户名是否为空
        if(StringUtils.isBlank(username)){


            return ServerResponse.error(ResponseEnum.REEOR_USER_NULL);
        }
        //判断验证码是否为空，是否输入正确

        if(StringUtils.isBlank(verifyCode)){
            return ServerResponse.error(ResponseEnum.ERROR_USER_CODE_NULL);

        }
        String code = (String) request.getSession().getAttribute(SystemConst.VERIFY_CODE);

        if(!verifyCode.equals(code)){

            return ServerResponse.error(ResponseEnum.ERROR_USER_CODE);
        }

        //查询数据库

        User dbUser = iUserService.loginUser(user.getUsername());


        //判断当前用户是否存在
        if(null == dbUser){

            return ServerResponse.error(ResponseEnum.ERROR_USER_NAME);

        }

        //判断今天是否登录错误三次
        if(dbUser.getStatus()==3){

            return ServerResponse.error(ResponseEnum.ERROR_USER_PWD_COUNT_ERROR);
        }


        //判断密码是否正确
        String dbuserPass = dbUser.getUserpass();
        Date loginErrorTime = dbUser.getLoginErrorTime();
        Integer loginErrorCount = dbUser.getLoginErrorCount();
        if(!userpass.equals(dbuserPass)){
            //判断错误次数和时间是否为空，为空就设置登陆错误次数=1和时间
            if(null==loginErrorTime){
                iUserService.updateLoginError(dbUser.getUserid());
                //修改内存中的错误次数
                dbUser.setLoginErrorCount(1);
            }

            //设置当天登录错误次数,三次就会锁定，如果是第二天就为1
            if(TimeUtil.date2String(loginErrorTime,TimeUtil.YMD).equals(TimeUtil.date2String(new Date(),TimeUtil.YMD))){
               //判断是否已经错误两次，算上当前这一次已经是第三次了，修改status为2
                if (loginErrorCount==2){

                    iUserService.updateLoginStatus(dbUser.getUserid());

                }

                iUserService.updateLoginErrorCount(dbUser.getUserid());

                //修改内存中的错误次数
                dbUser.setLoginErrorCount(loginErrorCount+1);

            }else {
                iUserService.updateLoginError(dbUser.getUserid());
                //修改内存中的错误次数
                dbUser.setLoginErrorCount(1);

            }


            return ServerResponse.error(ResponseEnum.ERROR_USER_PWD);
        }

        //所有验证通过，设置登陆错误次数为0，错误时间为空
        if(null!=loginErrorTime){

            iUserService.updateLoginErrorBuild(dbUser.getUserid());
        }

        //设置上次登录时间
        Date lastLoginTime = dbUser.getLastLoginTime();
        Integer todayLoginCount = dbUser.getTodayLoginCount();
        //判断上次登陆时间是否为空,也就是第一次登录
        if(null==lastLoginTime){

            iUserService.updateLastTimeAndCount(dbUser.getUserid());

        }else {

            if (TimeUtil.date2String(lastLoginTime,TimeUtil.YMD).equals(TimeUtil.date2String(new Date(),TimeUtil.YMD))){

                iUserService.updateLoginTimeAndCountInfo(dbUser.getUserid());
                //修改内存中的登录次数
                dbUser.setTodayLoginCount(todayLoginCount+1);


            }else {

                iUserService.updateLastTimeAndCount(dbUser.getUserid());
                //修改内存中的登录次数
                dbUser.setTodayLoginCount(1);
            }


        }

        //





        //清空验证码session
        request.getSession().removeAttribute(SystemConst.VERIFY_CODE);

        //把数据库查到的用户信息放到session中
        request.getSession().setAttribute(SystemConst.USER_MASSAGE,dbUser);


        return ServerResponse.success();



    }
}
