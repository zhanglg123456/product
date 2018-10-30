package com.fh.biz.log;

import com.fh.mapper.log.ISysLogMapper;
import com.fh.po.log.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysLogService")
public class ISysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogMapper sysLogMapper;
    @Override
    public void addLogToDB(SystemLog systemLog) {
        sysLogMapper.addLogToDB(systemLog);
    }
}
