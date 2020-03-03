package top.yangzefeng.integration.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * http相关工具类
 * 说明：该工具类仅对http请求方法进行封装
 *
 * @author MoCha
 * @date 2019/10/17
 */
@Slf4j
final class HttpUtils {
    /**
     * 发送Get请求
     *
     * @param url    请求url
     * @param params 请求参数
     * @return 响应结果
     */
    static String doGet(String url, Map<String, String> params) {
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                for (String key : params.keySet()) {
                    // 拼接请求参数
                    builder.addParameter(key, params.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            log.error("发送GET请求异常，异常信息：{}", e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                log.error("关闭流异常，异常信息：{}", e.getMessage());
            }
        }
        return resultString;
    }

    /**
     * 阻止实例化
     * <p>
     * Prevents instantiation
     */
    private HttpUtils() {
    }
}
