package com.fh.biz.user;

import com.fh.po.admin.User;

public interface IUserService {
    User loginUser(String username);

    void updateLoginError(Integer userid);

    void updateLoginErrorCount(Integer userid);

    void updateLastTimeAndCount(Integer userid);

    void updateLoginTimeAndCountInfo(Integer userid);

    void updateLoginErrorBuild(Integer userid);

    void updateLoginStatus(Integer userid);
}
