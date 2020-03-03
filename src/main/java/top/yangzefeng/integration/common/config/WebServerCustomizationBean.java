package top.yangzefeng.integration.common.config;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * 解决Buffer pool was not set on WebSocketDeploymentInfo问题警告
 * 看提示是让设置一下buffer pool，不然就使用默认的，这个警告不影响使用，但是看着别扭
 * 官方文档：
 * <a https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-programmatic-embedded-container-customization></a>
 *
 * @author MoCha
 * @date 2019/9/30
 */
@Component
public class WebServerCustomizationBean implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {
    @Override
    public void customize(UndertowServletWebServerFactory factory) {
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
            webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, 1024));
            deploymentInfo.addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo", webSocketDeploymentInfo);
        });
    }
}

