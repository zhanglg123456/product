package com.fh.api.sms.controller;

import com.fh.api.common.ResponseEnum;
import com.fh.api.common.ServerResponse;
import com.fh.api.common.SysCost;
import com.fh.api.sms.biz.ISmsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Resource(name = "aliyunSmsService")
    private ISmsService smsService;

    @RequestMapping(value = "sendCode",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public Object sendCode(String phone,String callback){

        if(StringUtils.isBlank(callback)){
            if(StringUtils.isBlank(phone)){
                return ServerResponse.error(ResponseEnum.ERROR_PHONE_CODE_BLANK);
            }
            if(phone.length()!=SysCost.PHONE_NUMBER_LENGTH){
                return ServerResponse.error(ResponseEnum.ERROR_PHONE__FORMAT);

            }
            return smsService.sendCode(phone);
        }else {
            if(StringUtils.isBlank(phone)){
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(ServerResponse.error(ResponseEnum.ERROR_PHONE_CODE_BLANK));
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
            if(phone.length()!=SysCost.PHONE_NUMBER_LENGTH){

                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(ServerResponse.error(ResponseEnum.ERROR_PHONE__FORMAT));
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;

            }
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(smsService.sendCode(phone));
            mappingJacksonValue.setJsonpFunction(callback);

            return mappingJacksonValue;

        }


    }
}
