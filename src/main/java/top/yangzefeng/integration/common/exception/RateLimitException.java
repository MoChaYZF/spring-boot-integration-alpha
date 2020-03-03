package top.yangzefeng.integration.common.exception;

/**
 * 限流注解异常
 *
 * @author MoCha
 * @date 2019/9/13
 */
public class RateLimitException extends RuntimeException {
    public RateLimitException(String message){
        super(message);
    }
}
