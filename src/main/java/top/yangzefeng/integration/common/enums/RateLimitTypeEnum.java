package top.yangzefeng.integration.common.enums;

/**
 * 接口限流限制类型参考 枚举类
 *
 * @author MoCha
 * @date 2019/9/13
 */
public enum RateLimitTypeEnum {
    /**
     * 自定义key（当使用该实例时，表明需要自定义存储在Redis的key）
     */
    CUSTOM,

    /**
     * 请求者IP
     */
    IP
}
