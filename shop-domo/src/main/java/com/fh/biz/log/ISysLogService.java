package com.fh.biz.log;

import com.fh.po.log.SystemLog;

public interface ISysLogService {

    public void addLogToDB(SystemLog systemLog);
}
