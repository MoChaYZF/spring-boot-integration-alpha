package top.yangzefeng.integration.carousel.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yangzefeng.integration.annotation.RateLimit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试限流控制器
 *
 * @author MoCha
 * @date 2019/10/18
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestLimitController {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 测试限流注解
     */
    @ResponseBody
    @RateLimit(limitPeriod = 100, limitCount = 10)
    @GetMapping("/testLimiter")
    public int testLimiter() {
        // 意味着 100S 内最多允许访问10次
        return ATOMIC_INTEGER.incrementAndGet();
    }
}
