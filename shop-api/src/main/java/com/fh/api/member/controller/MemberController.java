package com.fh.api.member.controller;

import com.fh.api.common.ResponseEnum;
import com.fh.api.common.ServerResponse;
import com.fh.api.common.SysCost;
import com.fh.api.member.biz.IMemberService;
import com.fh.api.member.po.Member;
import com.fh.api.member.vo.MemberVo;
import com.fh.api.util.CacheManager;
import com.fh.api.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin("*")
public class MemberController {

    @Resource(name = "memberService")
    private IMemberService memberService;


    @RequestMapping(value = "member/reg",method = RequestMethod.POST ,produces = "application/json;charset=utf-8")
    public ServerResponse regMember(MemberVo memberVo){
        String userName = memberVo.getUserName();
        String userPwd = memberVo.getUserPwd();
        String againUserPwd = memberVo.getAgainUserPwd();
        String phone = memberVo.getPhone();
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(userPwd)){

            return ServerResponse.error(ResponseEnum.ERROR_USER_NULL);
        }

        if(!userPwd.equals(againUserPwd)){


            return ServerResponse.error(ResponseEnum.ERROR_USER_PWD_EQUA);
        }
        if(StringUtils.isBlank(phone)){

            return ServerResponse.error(ResponseEnum.ERROR_USER_PHONE_NULL);
        }
        if(phone.length()!= SysCost.PHONE_NUMBER_LENGTH){
            return ServerResponse.error(ResponseEnum.ERROR_PHONE__FORMAT);

        }

        //判断手机验证码
        String vifCode = memberVo.getVifCode();
        if(StringUtils.isBlank(vifCode)){

           return ServerResponse.error(ResponseEnum.ERROR_VIFCODE__BLANK);

        }

        if(vifCode.length()!=SysCost.VIFCODE_LENGTH_LENGTH){
            return ServerResponse.error(ResponseEnum.ERROR_VIFCODE_FORMAT);

        }
        //获得oscach缓存中的数据
       /* String obj = (String) CacheManager.getInstance().getObj(phone);*/
        //获得redis缓存中的数据

        String obj = RedisUtil.get(phone);
        if(StringUtils.isBlank(obj)){

           return ServerResponse.error(ResponseEnum.ERROR_VIFCODE__TIMEOUT);
        }
        if(!vifCode.equals(obj)){
           return ServerResponse.error(ResponseEnum.ERROR_VIFCODE);
        }


        Member DBmember = memberService.findMemberByName(userName);


        if(null!=DBmember){

            return ServerResponse.error(ResponseEnum.ERROR_USER_REPEAT);
        }

        //查询手机号码是否存在
        String dbPhone = memberService.findMenberByPhone(phone);
        if(null!=dbPhone){

            return ServerResponse.error(ResponseEnum.ERROR_USER_PHONE_EQUA);
        }
        memberService.addMember(memberVo);

        return ServerResponse.success();


    }
}
