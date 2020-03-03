package top.yangzefeng.integration.common.util;

import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.http.HttpHeaders;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 获取浏览器用户代理信息
 *
 * @author MoCha
 * @date 2019/12/19
 */
public final class UserAgentUtils {
    /**
     * 获取浏览器用户代理信息
     */
    public static Map<String, Object> getUserAgentInfo() {
        String userAgentString = WebUtils.getRequest().getHeader(HttpHeaders.USER_AGENT);
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("OS", userAgent.getOperatingSystem().getName());
        map.put("browser", userAgent.getBrowser().getName().concat(" ").concat(userAgent.getBrowserVersion().getMajorVersion()));
        return map;
    }

    /**
     * 阻止实例化
     * <p>
     * Prevents instantiation
     */
    private UserAgentUtils() {
    }
}
