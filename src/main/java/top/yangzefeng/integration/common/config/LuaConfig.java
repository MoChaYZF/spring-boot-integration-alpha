package top.yangzefeng.integration.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * Lua脚本配置类
 *
 * @author MoCha
 * @date 2019/9/14
 */
@Configuration
public class LuaConfig {
    @Value("${lua.ratelimit.script.location}")
    private String luaRateLimitScriptLocation;

    /**
     * 注入加载“接口限流”功能的Lua脚本Bean
     *
     * @return DefaultRedisScript<Number>
     */
    @Bean
    public DefaultRedisScript<Number> redisRateLimitScript() {
        // 加载Lua脚本
        DefaultRedisScript<Number> redisScript = new DefaultRedisScript<>();
        redisScript.setLocation(new ClassPathResource(luaRateLimitScriptLocation));
        redisScript.setResultType(Number.class);
        return redisScript;
    }
}
