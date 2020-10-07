package top.yangzefeng.integration.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
}
