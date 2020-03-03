package top.yangzefeng.integration.common.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Druid 监控界面配置类
 *
 * @author MoCha
 * @date 2020/3/3
 */
@Configuration
public class DruidConfig {
    /**
     * 注册一个StatViewServlet
     *
     * @return servlet registration bean
     */
    @Bean
    @SuppressWarnings("unchecked")
    public ServletRegistrationBean druidStatViewServlet() {
        // 配置监控页面的访问路径（/druid, /druid/login.html ...）
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");

        // TODO 替换为自己登录Druid监控界面的用户名和密码
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     *
     * @return filter registration bean
     */
    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean druidStatFilter() {

        FilterRegistrationBean filterRegistrationBean =
                new FilterRegistrationBean(new WebStatFilter());

        // 添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");

        // 添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
