package com.fh.api.sms.biz;

import com.fh.api.common.ServerResponse;

public interface ISmsService {
    ServerResponse sendCode(String phone);


}
