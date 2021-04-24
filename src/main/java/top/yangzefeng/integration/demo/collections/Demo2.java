package top.yangzefeng.integration.demo.collections;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * 描述 : .
 *
 * @author : MoCha
 * @version : v1
 * @date : 2021-03-23 08:58
 */
@Slf4j
public class Demo2 {
    public static void main(String[] args) {
        List<String> firstList = Lists.newArrayList("1", "2", "3");
        List<String> secondaryList = Lists.newArrayList("3", "4", "5");

        log.info("firstList = {}", firstList);
        log.info("secondaryList = {}", secondaryList);

        // 并集
        testUnion(firstList, secondaryList);

        // 交集
        testIntersection(firstList, secondaryList);

        // 差集
        testSubtract(firstList, secondaryList);

        // 交集的补集（析取）
        testDisjunction(firstList, secondaryList);
    }

    /**
     * 并集
     */
    private static void testUnion(List<String> firstList, List<String> secondaryList) {
        Collection<String> unionCollection = CollectionUtils.union(firstList, secondaryList);
        log.info("并集 = {}", unionCollection);
    }

    /**
     * 交集
     */
    private static void testIntersection(List<String> firstList, List<String> secondaryList) {
        Collection<String> intersectionCollection = CollectionUtils.intersection(firstList, secondaryList);
        log.info("交集 = {}", intersectionCollection);
    }

    /**
     * 差集
     */
    private static void testSubtract(List<String> firstList, List<String> secondaryList) {
        Collection<String> subtractCollection = CollectionUtils.subtract(firstList, secondaryList);
        log.info("差集 = {}", subtractCollection);
    }

    /**
     * 交集的补集（析取）
     */
    private static void testDisjunction(List<String> firstList, List<String> secondaryList) {
        Collection<String> disjunctionCollection = CollectionUtils.disjunction(firstList, secondaryList);
        log.info("交集的补集（析取） = {}", disjunctionCollection);
    }
}
