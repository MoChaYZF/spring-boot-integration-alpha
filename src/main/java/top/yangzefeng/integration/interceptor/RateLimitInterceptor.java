package top.yangzefeng.integration.interceptor;

import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import top.yangzefeng.integration.annotation.RateLimit;
import top.yangzefeng.integration.common.enums.RateLimitTypeEnum;
import top.yangzefeng.integration.common.exception.RateLimitException;
import top.yangzefeng.integration.common.util.WebUtils;

import java.lang.reflect.Method;

/**
 * 限流拦截器
 *
 * @author MoCha
 * @date 2019/9/13
 */
@Slf4j
@Aspect
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RateLimitInterceptor {
    private final RedisTemplate<String, Object> redisTemplate;

    private final DefaultRedisScript<Number> redisRateLimitScript;

    /**
     * 任意公共方法的执行
     * 任何一个执行的方法有一个 @RateLimit annotation的连接点
     *
     * execution(public * *(..))
     * 第一个参数表示类型
     * 第二个参数表示返回值
     * 第三个参数表示方法名
     * (..)表示任意入参
     */
    @Around("execution(public * *(..)) && @annotation(top.yangzefeng.integration.annotation.RateLimit)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        // 获取接口限流注解
        RateLimit limitAnnotation = method.getAnnotation(RateLimit.class);

        String key;
        String name = limitAnnotation.name();
        // 限流限制时间段
        int limitPeriod = limitAnnotation.limitPeriod();
        // 限制时间段内最多可访问次数
        int limitCount = limitAnnotation.limitCount();
        RateLimitTypeEnum limitType = limitAnnotation.limitType();

        // 根据接口限流限制类型，进行键key设置
        switch (limitType) {
            case IP:
                // 根据 IP + API 限流
                key = WebUtils.getIpAddress() + WebUtils.getUri();
                break;
            case CUSTOM:
                key = limitAnnotation.key();
                break;
            default:
                key = StringUtils.upperCase(method.getName());
        }

        // 创建一个不可变集合
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnotation.keyPrefix(), key));
        Number currentCount = redisTemplate.execute(redisRateLimitScript, keys, limitCount, limitPeriod);
        log.info("限制时间：{}, 限制调用次数：{}, 当前调用次数：{}, name = {}, key = {}", limitPeriod, limitCount, currentCount, name, key);
        if (currentCount != null && currentCount.intValue() <= limitCount) {
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("服务器异常！");
            }
        } else {
            throw new RateLimitException("您的操作过于频繁，请稍后重试！");
        }
    }
}
