package com.person.system.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 采用实现HttpServletRequestWrapper自定义一个包装器的方式解决输入流不能重复读取的问题
 */
public class BufferedRequestWrapper extends HttpServletRequestWrapper {
    private byte[] requestBodyBytes;

    //在类的序列化过程中忽略这些字段
    private transient ServletInputStream inputStream;
    private transient BufferedReader reader;

    public BufferedRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        // 一次性将请求体内容读取并缓存到requestBodyBytes中
        requestBodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (inputStream == null) {
            inputStream = new BufferedServletInputStream();
        }
        return inputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(getInputStream()));
        }
        return reader;
    }

    // 自定义ServletInputStream以实现多次读取
    private class BufferedServletInputStream extends ServletInputStream {

        private ByteArrayInputStream buffer;

        public BufferedServletInputStream() {
            buffer = new ByteArrayInputStream(requestBodyBytes);
        }

        @Override
        public int read() throws IOException {
            return buffer.read();
        }

        @Override
        public boolean isFinished() {
            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    // 如果需要以String形式获取请求体内容
    public String getRequestBody() throws IOException {
        return new String(requestBodyBytes, getCharacterEncoding());
    }

    // 可选：将请求体反序列化为JSON对象
    public <T> T getRequestBodyAs(Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(requestBodyBytes, clazz);
    }
}
