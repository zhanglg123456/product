package com.fh.mapper.log;

import com.fh.po.log.SystemLog;

public interface ISysLogMapper {
    void addLogToDB(SystemLog systemLog);
}
