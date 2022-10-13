package cn.iocoder.yudao.framework.web.core.filter;

import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Request Body 缓存 Wrapper，参考：https://blog.csdn.net/yaomingyang/article/details/108246334
 *
 * @author 芋道源码
 */
public class CacheRequestBodyWrapper extends HttpServletRequestWrapper {

    /**
     * 缓存的内容
     */
    private final byte[] body;

    public CacheRequestBodyWrapper(HttpServletRequest request) {
        super(request);
        body = ServletUtil.getBodyBytes(request);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    /**
     * 每次调用此方法时将数据流中的数据读取出来，然后再回填到 InputStream 之中
     * 解决通过@RequestBody读取一次后控制器拿不到参数问题
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body);
        // 返回 ServletInputStream
        return new ServletInputStream() {

            @Override
            public int read() {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {}

            @Override
            public int available() {
                return body.length;
            }

        };
    }

}
