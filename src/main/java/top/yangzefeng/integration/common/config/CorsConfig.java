package top.yangzefeng.integration.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * 跨域处理配置
 * <p>
 * 说明：
 * 1. 只要协议、IP、http方法任意一个不同，即为跨域
 * 2. CORS全称是"跨域资源共享"（Cross-origin resource sharing）。它允许浏览器向跨源(协议 + 域名 + 端口)服务器，发出XMLHttpRequest请求，从而克服了AJAX只能同源使用的限制。
 * 3. 前后分离架构下肯定会遇到跨域的问题，因为请求都是通过微服务网关来转发的，所以我们可以在网关模块来统一处理跨域。
 *
 * @author MoCha
 * @date 2019/11/16
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 表示允许cookie跨域
        corsConfiguration.setAllowCredentials(true);
        // 表示请求头部允许携带任何内容
        corsConfiguration.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
        // 表示允许任何来源
        corsConfiguration.setAllowedOrigins(Collections.singletonList(CorsConfiguration.ALL));
        // 表示允许任何HTTP方法
        corsConfiguration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
        // CORS 配置对所有接口都有效
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
