package top.yangzefeng.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.yangzefeng.integration.common.config.IceStreamBanner;

/**
 * 主启动类
 *
 * @author MoCha
 * @date 2020/03/02
 */
@SpringBootApplication
public class IntegrationAlphaApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(IntegrationAlphaApplication.class);
        springApplication.setBanner(new IceStreamBanner());
        springApplication.run(args);
    }
}
