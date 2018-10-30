package com.fh.mapper.user;

import com.fh.po.admin.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface IUserMapper {
    User loginUser(String username);

    void updateLoginError(@Param("userid") Integer userid,@Param("date") Date date);

    void updateLoginErrorCount(@Param("userid") Integer userid,@Param("date") Date date);

    void updateLastTimeAndCount(@Param("userid") Integer userid,@Param("date") Date date);

    void updateLoginTimeAndCountInfo(@Param("userid") Integer userid,@Param("date") Date date);

    void updateLoginErrorBuild(Integer userid);

    void updateLoginStatus(Integer userid);
}
