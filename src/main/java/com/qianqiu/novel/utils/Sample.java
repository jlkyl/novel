package com.qianqiu.novel.utils;

import com.baidu.aip.contentcensor.AipContentCensor;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "16619870";
    public static final String API_KEY = "lGQY4nIR7gq5EGEf9a2INY4L";
    public static final String SECRET_KEY = "cTEbcIyDsbDWhasirut8cO6dwTB6IDKU";
    public static AipContentCensor aipContentCensor = null;

    public static AipContentCensor getAip(){
        // 初始化一个AipContentCensor
        if(null == aipContentCensor)
            return new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);
        else
            return aipContentCensor;
    }
}