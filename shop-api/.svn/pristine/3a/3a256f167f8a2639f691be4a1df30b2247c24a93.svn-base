package com.fh.api.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendVifCode {

    public static void main(String[] args) throws IOException {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.netease.im/sms/sendcode.action");
        //设置请求头
        String nonce = CheckSumBuilder.getRandom();
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum("178673793aec", nonce, curTime);
        httpPost.addHeader("AppKey","80a8664159a04576d2248ed4fc03ccec");
        httpPost.addHeader("Nonce",nonce);
        httpPost.addHeader("CurTime",curTime);
        httpPost.addHeader("CheckSum",checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");


        //设置请求body,requestBody参数,键值对


        //创建一个普通的键值对[普通传参],mobiles可传数组,value可以是个集合,多个手机号码
        //content-type:application/x-www-form-urlencoded
        BasicNameValuePair mobile = new BasicNameValuePair("mobile", "17839015082");
        //设置验证码长度
        BasicNameValuePair codeLen = new BasicNameValuePair("codeLen", "6");
        //设置短信模板
        BasicNameValuePair templateid = new BasicNameValuePair("templateid", "9424072");
        //新建一个list集合
        List<BasicNameValuePair> valuePairs = new ArrayList<>();
        valuePairs.add(mobile);
        valuePairs.add(codeLen);
        valuePairs.add(templateid);
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs,"utf-8");
        httpPost.setEntity(formEntity);


        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        System.out.println(result);
    }
}
