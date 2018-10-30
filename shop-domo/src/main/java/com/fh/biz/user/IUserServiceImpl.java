package com.fh.biz.user;

import com.fh.mapper.user.IUserMapper;
import com.fh.po.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("iUserService")
public class IUserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper iUserMapper;



    @Override
    public User loginUser(String username) {
        return iUserMapper.loginUser(username);
    }

    @Override
    public void updateLoginError(Integer userid) {

        iUserMapper.updateLoginError(userid, new Date());
    }

    @Override
    public void updateLoginErrorCount(Integer userid) {
        iUserMapper.updateLoginErrorCount(userid, new Date());
    }

    @Override
    public void updateLastTimeAndCount(Integer userid) {
        iUserMapper.updateLastTimeAndCount(userid, new Date());
    }

    @Override
    public void updateLoginTimeAndCountInfo(Integer userid) {
        iUserMapper.updateLoginTimeAndCountInfo(userid, new Date());
    }

    @Override
    public void updateLoginErrorBuild(Integer userid) {
        iUserMapper.updateLoginErrorBuild(userid);
    }

    @Override
    public void updateLoginStatus(Integer userid) {
        iUserMapper.updateLoginStatus(userid);
    }
}
