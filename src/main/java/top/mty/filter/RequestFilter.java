package top.mty.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @Author mty
 * @Date 2021/4/19
 * @Description filter
 */
@Component
public class RequestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    public static class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private final byte[] body;

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request The request to wrap
         * @throws IllegalArgumentException if the request is null
         */
        public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            body = getBodyString(request).getBytes(StandardCharsets.UTF_8);
        }

        public String getBodyString(final HttpServletRequest request) {
            StringBuilder sb = new StringBuilder();
            String result = "";
            InputStream inputStream = null;
            BufferedReader bufferedReader = null;
            try {
                inputStream = cloneInputStream(request.getInputStream());
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                JSONObject jsonObject = JSONObject.parseObject(sb.toString());
                if (jsonObject == null) {
                    jsonObject = new JSONObject();
                }
                jsonObject.put("queryUrl", request.getRequestURI());
                result = JSON.toJSONString(jsonObject);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        }

        public InputStream cloneInputStream(ServletInputStream inputStream) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            try {
                while ((len = inputStream.read(buffer)) > -1) {
                    byteArrayOutputStream.write(buffer, 0, len);
                }
                byteArrayOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }

        @Override
        public BufferedReader getReader() {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }

        @Override
        public ServletInputStream getInputStream() {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
            return new ServletInputStream() {
                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener listener) {

                }

                @Override
                public int read() throws IOException {
                    return byteArrayInputStream.read();
                }
            };
        }
    }




}
