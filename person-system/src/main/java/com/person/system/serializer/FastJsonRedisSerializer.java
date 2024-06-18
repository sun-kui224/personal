package com.person.system.serializer;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.Filter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @author sk
 * @date 2024/5/13
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    private static final Log log = LogFactory.getLog(FastJsonRedisSerializer.class);
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 自动识别json对象白名单配置（仅允许解析的包名）
     */
    public static final String[] JSON_WHITELIST_STR = { "org.springframework", "com.person" };

    static final Filter AUTO_TYPE_FILTER = JSONReader.autoTypeFilter(JSON_WHITELIST_STR);

    private final Class<T> clazz;

    public FastJsonRedisSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        try {
            return JSON.toJSONString(t, JSONWriter.Feature.WriteClassName).getBytes(DEFAULT_CHARSET);
        } catch (Exception e) {
            throw new SerializationException("序列化失败：" + e.getMessage(), e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        try {
            String str = new String(bytes, DEFAULT_CHARSET);
            return JSON.parseObject(str, clazz,AUTO_TYPE_FILTER);
        } catch (Exception e) {
            throw new SerializationException("反序列化失败：" + e.getMessage(), e);
        }
    }
}
