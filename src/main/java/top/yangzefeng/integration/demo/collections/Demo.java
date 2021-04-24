package top.yangzefeng.integration.demo.collections;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述 : .
 * <p>
 * 差集 差集得到的结果与入参顺序有关，结果为第一个入参存在的值
 * CollectionUtils.subtract(Iterable<? extends O> a, Iterable<? extends O> b)
 * <p>
 * 并集
 * CollectionUtils.union(Iterable<? extends O> a, Iterable<? extends O> b)
 * <p>
 * 交集
 * CollectionUtils.intersection(Collection a, Collection b)
 *
 * @author : MoCha
 * @version : v1
 * @date : 2021-03-20 11:51
 */
@Slf4j
public class Demo {
    public static void main(String[] args) {
        List<String> outSideFirstTypeList = Lists.newArrayList("1", "2");
        List<String> inSideFirstTypeList = Lists.newArrayList("1", "3", "4");

        Collection<String> resultCollection = testCollectionUtils(outSideFirstTypeList, inSideFirstTypeList);
        log.info("resultCollection = {}", resultCollection);

        Collection<String> mergeCollection = doMerge(outSideFirstTypeList, inSideFirstTypeList);
        log.info("mergeCollection = {}", mergeCollection);
    }

    private static Collection<String> testCollectionUtils(List<String> sourceList, List<String> targetList) {
        return CollectionUtils.subtract(sourceList, targetList);
    }

    private static List<String> doMerge(List<String> sourceList, List<String> targetList) {
        return sourceList.stream().filter(targetList::contains).collect(Collectors.toList());
    }

}
