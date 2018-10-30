package com.fh.api.sms.biz;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fh.api.common.ServerResponse;
import com.fh.api.util.CacheManager;
import com.fh.api.util.RedisUtil;
import com.fh.api.util.SMSUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("aliyunSmsService")
public class IAliyunSmsServiceImpl implements ISmsService {


    @Value("${aliy.sms.product}")
    private String product;

    @Value("${aliy.sms.domain}")
    private String domain;

    @Value("${aliy.sms.defaultConnectTimeout}")
    private String defaultConnectTimeout;

    @Value("${aliy.sms.defaultReadTimeout}")
    private String defaultReadTimeout;

    @Value("${aliy.sms.timeValue}")
    private String  timeValue;

    @Value("${aliy.sms.regionId}")
    private  String region;

    @Value("${aliy.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliy.sms.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliy.sms.signName}")
    private String signName;

    @Value("${aliy.sms.templateCode}")
    private String templateCode;


    @Override
    public ServerResponse sendCode(String phone) {
        //调用lang3jar包里的RandomStringUtils生成随机验证码
        String code = RandomStringUtils.randomNumeric(6);
        //可自助调整超时时间
        System.setProperty(defaultConnectTimeout, timeValue);
        System.setProperty(defaultReadTimeout,timeValue);

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(region, accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint(region, region, product, domain);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
       /* request.setTemplateParam("{\"name\":\"Tom\", \"code\":\""+code+"\"}");*/
        request.setTemplateParam("{\"code\":\""+code+"\"}");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
            if(sendSmsResponse.getCode().equals("OK")){
                //存入oscach缓存
                /*CacheManager.getInstance().putObj(phone,code,60);*/
                //存入Redis缓存服务器
                RedisUtil.setEX(phone,code,60);
                return ServerResponse.success();
            }else {
                return ServerResponse.error(80001,"发送失败,阿里云错误码:"+sendSmsResponse.getMessage());
            }
    }
}
