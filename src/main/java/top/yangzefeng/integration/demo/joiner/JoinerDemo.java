package top.yangzefeng.integration.demo.joiner;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 描述 : guava Joiner Demo.
 *
 * @author : MoCha
 * @version : v1
 * @date : 2021-03-31 10:22
 */
@Slf4j
public class JoinerDemo {
    public static void main(String[] args) {
        List<String> arrayList = Lists.newArrayList("1", "2", "");
        String result = Joiner.on(StringPool.COMMA).join(arrayList);
        log.info("result = {}", result);
    }
}
