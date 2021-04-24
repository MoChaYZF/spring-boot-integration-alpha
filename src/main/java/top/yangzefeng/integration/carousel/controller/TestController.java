package top.yangzefeng.integration.carousel.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yangzefeng.integration.carousel.domain.dto.CarouselDTO;
import top.yangzefeng.integration.carousel.service.CarouselService;
import top.yangzefeng.integration.common.page.PageQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 描述 : .
 *
 * @author : MoCha
 * @version : v1
 * @date : 2021-03-27 15:27
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {
    private final CarouselService carouselService;

    private final RedisTemplate<String, String> redisTemplate;

    private static final String LIKE = "1";

    private static final String UN_LIKE = "0";

    @PostMapping("/testEnum")
    public void testEnum(String type) {
        CompletableFuture.supplyAsync(() -> {
            carouselService.findCarouselDetailPage(new CarouselDTO(), new PageQuery());
            return "";
        });
    }

    @PostMapping("/testRedis")
    public void testRedis() {
        String userId = "123";
        String recordId = "456";
        String category = "1";
        String hashKey = userId + "::" + recordId + "::" + category;
        redisTemplate.opsForHash().put("MAP_KEY_THOUGHT_LIKED", hashKey, LIKE);
        redisTemplate.opsForHash().put("MAP_KEY_THOUGHT_LIKED", hashKey, UN_LIKE);

        String userId2 = "123";
        String recordId2 = "123";
        String category2 = "2";
        String hashKey2 = userId2 + "::" + recordId2 + "::" + category2;
        redisTemplate.opsForHash().put("MAP_KEY_THOUGHT_LIKED", hashKey2, UN_LIKE);
        redisTemplate.opsForHash().put("MAP_KEY_THOUGHT_LIKED", hashKey2, LIKE);

        List<String> recordIdList = new ArrayList<>(16);
        Map<String, String> recordMap = new HashMap<>(16);
        Map<Object, Object> mapKeyThoughtLiked = redisTemplate.opsForHash().entries("MAP_KEY_THOUGHT_LIKED");
        for (Map.Entry<Object, Object> objectObjectEntry : mapKeyThoughtLiked.entrySet()) {
            String key = (String) objectObjectEntry.getKey();
            String[] keyInfo = key.split("::");
            String userIdByRedis = keyInfo[0];
            String recordIdByRedis = keyInfo[1];
            String likeFlag = (String) objectObjectEntry.getValue();
            recordMap.put(userIdByRedis + "::" + recordIdByRedis, likeFlag);

            if ("1".equals(likeFlag)) {
                recordIdList.add(recordIdByRedis);
            }

        }

        Map<String, Long> map = recordIdList.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        map.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println(recordIdList);
        System.out.println(recordMap);

        Long o = (Long) redisTemplate.opsForHash().get("123", "123");
        System.out.println(o);
    }
}
