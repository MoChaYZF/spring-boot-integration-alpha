package top.yangzefeng.integration.annotation;

import top.yangzefeng.integration.common.enums.RateLimitTypeEnum;

import java.lang.annotation.*;

/**
 * 接口限流注解
 * <p>
 * 如果key与limitType同时出现，则以limitType为准
 * 如果两个都没有使用，则以调用方法名大写作为键key
 * <p>
 * 说明：为什么不用RateLimitTypeEnum.CUSTOM作为RateLimitTypeEnum的默认值
 * 1. 更希望通过限制ip来达到接口限流的作用
 * 2. 当用户需要自定义键key时，在使用@RateLimit的时候，显式写出LimitTypeEnum.CUSTOMER以及所自定义的key能够更容易理解和阅读
 *
 * @author MoCha
 * @date 2019/9/13
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RateLimit {
    /**
     * 资源的名字
     *
     * @return String
     */
    String name() default "";

    /**
     * Key的prefix
     *
     * @return String
     */
    String keyPrefix() default "rate_limit:";

    /**
     * 资源的key
     *
     * @return String
     */
    String key() default "";

    /**
     * 限制时间段（单位秒）
     *
     * @return int
     */
    int limitPeriod();

    /**
     * 限制时间段内最多可访问次数
     *
     * @return int
     */
    int limitCount();

    /**
     * 限制类型参考
     *
     * @return RateLimitTypeEnum
     */
    RateLimitTypeEnum limitType() default RateLimitTypeEnum.IP;
}
