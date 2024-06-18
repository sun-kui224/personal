package com.person.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.person.common.constant.Constants;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ip城市信息工具
 *
 * @author sk
 * @date 2024/4/3
 */
public class IpCityUtils {

    private static final Logger log = LoggerFactory.getLogger(IpCityUtils.class);

    //城市查询
    public static final String CITY_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    /**
     * 获取地址
     * @param ip
     * @return
     */
//    public static String getRealAddressByIP(String ip)
//    {
//        String rspStr;
//        try {
//            if (IpUtils.internalIp(ip)) {
//                rspStr = HttpUtils.sendGet(CITY_URL, "json=true", Constants.GBK);
//            } else {
//                rspStr = HttpUtils.sendGet(CITY_URL, "ip=" + ip + "&json=true", Constants.GBK);
//            }
//            if (StringUtils.isEmpty(rspStr))
//            {
//                log.error("获取地理位置异常 {}", ip);
//                return UNKNOWN;
//            }
//            JSONObject obj = JSON.parseObject(rspStr);
//            String region = obj.getString("pro");
//            String city = obj.getString("city");
//            return String.format("%s %s", region, city);
//        }
//        catch (Exception e)
//        {
//            log.error("获取地理位置异常 {}", ip);
//        }
//        return UNKNOWN;
//    }

}
