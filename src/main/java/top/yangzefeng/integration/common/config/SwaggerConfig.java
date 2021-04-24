package top.yangzefeng.integration.common.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;

/**
 * Swagger配置类
 *
 * @author MoCha
 * @date 2019/5/24
 */
@Configuration
@EnableSwagger2WebMvc
@Profile({"test", "dev"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SwaggerConfig {
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                // 将ApiInfo加入到Docket中
                .apiInfo(getApiInfo())
                .extensions(openApiExtensionResolver.buildSettingExtensions());
    }

    private ApiInfo getApiInfo() {
        // TODO 替换为自己的swagger配置
        return new ApiInfo(
                "SpringBoot Integration 工具集成箱的API文档",
                "关于SpringBoot Integration 工具集成箱的使用说明",
                "1.0.0",
                "https://gitee.com/MoChaYZF",
                new Contact("MoCha", "https://gitee.com/MoChaYZF", "xxx@qq.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
