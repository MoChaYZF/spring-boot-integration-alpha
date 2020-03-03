package top.yangzefeng.integration.common.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.yangzefeng.integration.common.enums.ResultStatusEnum;
import top.yangzefeng.integration.common.exception.CustomException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web工具类
 *
 * @author MoCha
 * @date 2019/9/14
 */
@Slf4j
public final class WebUtils {
    private static final String UNKNOWN = "unknown";

    /**
     * 本地ip
     */
    private static final String LOCAL_HOST = "0:0:0:0:0:0:0:1";

    /**
     * 获取ip地址
     * <p>
     * 说明：
     * 1. 使用 Nginx等反向代理软件，不能直接通过request.getRemoteAddr()获取 IP地址
     * 需要在Nginx配置文件中配置proxy_set_header X-Forwarded-For $remote_addr;
     *
     * @return ip地址字符串
     */
    public static String getIpAddress() {
        HttpServletRequest request = WebUtils.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (LOCAL_HOST.equals(ip)) {
            ip = "127.0.0.1";
        }
        // 用户有可能使用代理软件方式避免真实IP地址
        // 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
        // 多次反向代理后会有多个ip值，第一个ip才是真实ip
        if (ip.split(StringPool.COMMA).length > 1) {
            ip = ip.split(StringPool.COMMA)[0];
        }
        return ip;
    }

    /**
     * 获取资源请求路径
     *
     * @return 如获取到/getAllUser
     */
    public static String getUri() {
        HttpServletRequest request = WebUtils.getRequest();
        return request.getRequestURI();
    }

    /**
     * 获取当前请求的Request对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new CustomException(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        } else {
            return requestAttributes.getRequest();
        }
    }

    /**
     * 获取当前请求的Response对象
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new CustomException(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        } else {
            return requestAttributes.getResponse();
        }
    }

    /**
     * 阻止实例化
     * <p>
     * Prevents instantiation
     */
    private WebUtils() {
    }
}
