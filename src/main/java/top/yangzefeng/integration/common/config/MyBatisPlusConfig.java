package top.yangzefeng.integration.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis-Plus配置类
 * <p>
 * 说明：
 * 1. 当注入的Bean对象是简单的new方式注入，那么可以采用@Import的方式来简化代码
 *
 * @author MoCha
 * @date 2019/9/21
 */
@Configuration
@EnableTransactionManagement
@MapperScan("top.yangzefeng.**.mapper")
@Import(PaginationInterceptor.class)
public class MyBatisPlusConfig {
    /**
     * SQL性能分析插件
     * 开发环境使用，线上不推荐
     * 设置 dev test 环境开启
     */
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor().setFormat(true).setMaxTime(10000L);
    }
}
