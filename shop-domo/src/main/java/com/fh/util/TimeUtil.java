package com.fh.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @类描述 ：日期工具类
 * @作者 ：朱晨玺
 * @邮箱 : 2354469913@qq.com
 * @创建日期 ：2018年08月31日 15:40
 */
public class TimeUtil {
    public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD = "yyyy-MM-dd";

    public static Date StrToDate(String date, String pattern) {
        if (StringUtils.isNoneBlank(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date result = null;
            try {
                result = sdf.parse(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return result;
        } else {
            return null;
        }
    }

    public static String date2String(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String result = sdf.format(date);
        return result;
    }
}
