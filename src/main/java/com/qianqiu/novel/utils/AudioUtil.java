package com.qianqiu.novel.utils;
/*
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;*/
import org.json.JSONObject;

import java.util.Map;


public class AudioUtil {
    public static int audioTxt(String str){
        JSONObject res = Sample.getAip().antiSpam(str, null);
        String result = res.get("result").toString();
        Map<String,Object> maps = (Map)GsonUtil.GsonToMaps(result);
        System.out.println(maps.get("spam"));
        return Integer.parseInt(new java.text.DecimalFormat("0").format(maps.get("spam")));
    }

    public static void main(String[] args) {
        int res = audioTxt("几个点提一下，咨询.1.4.7～0.0.0.6～6.3.7.2。1.【配套齐全】 共享城东大配套，周边4大综合体齐聚:中骏世界城、星光耀广场、华大泰禾广场、润柏 香港城;");
        System.err.println(res);
    }
}
