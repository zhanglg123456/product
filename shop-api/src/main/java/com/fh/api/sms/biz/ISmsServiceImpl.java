package com.fh.api.sms.biz;

import com.fh.api.common.ServerResponse;
import com.fh.api.test.CheckSumBuilder;
import com.fh.api.util.CacheManager;
import com.fh.api.util.HttpClientUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("smsService")
public class ISmsServiceImpl implements ISmsService {

    @Value("${sms.url}")
    private String url;

    @Value("${sms.secret}")
    private String secret;

    @Value("${sms.appkey}")
    private String  appkey;




    @Override
    public ServerResponse sendCode(String phone) {

        //获得随机数
        String nonce = CheckSumBuilder.getRandom();
        //当前时间戳
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        //sha1算法生成的字符串
        String checkSum = CheckSumBuilder.getCheckSum(secret, nonce, curTime);
        //发送短信,组装请求头
        Map headers = new HashMap();
        headers.put("AppKey",appkey);
        headers.put("Nonce",nonce);
        headers.put("CurTime",curTime);
        headers.put("CheckSum",checkSum);
        //组装参数
        Map params = new HashMap();
        params.put("mobile",phone);
        //设置验证码长度
        params.put("codeLen","6");
        //设置短信模板
        params.put("templateid","9424072");
        String result = HttpClientUtil.sendPost(url, headers, params);
        //转为java对象
        Gson gson = new Gson();
        Map map = gson.fromJson(result, Map.class);
        int code = ((Double)map.get("code")).intValue();
        if(code==200){
            //存入缓存
            String vifCode = (String) map.get("obj");
            CacheManager.getInstance().putObj(phone,vifCode,60);
            return ServerResponse.success();
        }else {
           return ServerResponse.error(80001,"发送失败,网易云错误码:"+code);
        }

    }

}
