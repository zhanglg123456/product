package com.fh.api.app.biz;

import com.fh.api.app.mapper.IAppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @类描述：(请填写这个类的作用)
 * @作者        ：张鹿阁
 * @邮箱        : 200766243@qq.com
 * @创建日期    ：2018年09月29日 19:15
 */
@Service("appService")
public class IAppServiceImpl implements IAppService {
    
    @Autowired
    private IAppMapper appMapper;
    
    @Override
    public String getAppSecret(String appKey) {

        return appMapper.getAppSecret(appKey);
    }
}
