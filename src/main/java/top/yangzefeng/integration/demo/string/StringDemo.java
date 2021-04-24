package top.yangzefeng.integration.demo.string;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.springframework.util.StopWatch;

import java.util.*;

/**
 * 描述 : .
 *
 * @author : MoCha
 * @version : v1
 * @date : 2021-04-01 19:41
 */
public class StringDemo {
    public static void main(String[] args) {
        // System.out.println(randomString());
        // String sourceFirstType = "1,2,3,45,7,1,2,13,34,4143,143,13";
        // String targetFirstType = "1,2,3,45,7,1,2,13,34,4143,143,13";
        String sourceFirstType = randomString();
        System.out.println("==========");
        String targetFirstType = randomString();

        System.out.println(sourceFirstType);
        System.out.println(targetFirstType);

        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println(mapInspect(sourceFirstType, targetFirstType));
        watch.stop();

        System.out.println("mapInspect: " + watch.getTotalTimeMillis());

        StopWatch watch2 = new StopWatch();
        watch2.start();
        System.out.println(inspectFirstTypeChange(sourceFirstType, targetFirstType));
        watch2.stop();

        System.out.println("inspectFirstTypeChange: " + watch2.getTotalTimeMillis());
    }

    private static boolean mapInspect(String sourceFirstType, String targetFirstType) {
        if (sourceFirstType == null) {
            sourceFirstType = "";
        }
        if (targetFirstType == null) {
            targetFirstType = "";
        }
        String[] sourceFirstTypeArray = sourceFirstType.split(StringPool.COMMA);
        String[] targetFirstTypeArray = targetFirstType.split(StringPool.COMMA);

        Map<String, Integer> sourceFirstTypeMap = new HashMap<>(sourceFirstTypeArray.length);
        for (int i = 0; i < sourceFirstTypeArray.length; i++) {
            sourceFirstTypeMap.put(sourceFirstTypeArray[i], i);
        }

        for (String targetFirst : targetFirstTypeArray) {
            sourceFirstTypeMap.remove(targetFirst);
        }

        return sourceFirstTypeMap.size() > 0;
    }

    private static boolean inspectFirstTypeChange(String sourceFirstType, String targetFirstType) {
        if (sourceFirstType == null) {
            sourceFirstType = "";
        }
        if (targetFirstType == null) {
            targetFirstType = "";
        }
        String[] sourceFirstTypeArray = sourceFirstType.split(StringPool.COMMA);
        String[] targetFirstTypeArray = targetFirstType.split(StringPool.COMMA);
        Arrays.sort(sourceFirstTypeArray);
        Arrays.sort(targetFirstTypeArray);
        return !Arrays.equals(sourceFirstTypeArray, targetFirstTypeArray);
    }

    private static String randomString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            int randIntResult = new Random().nextInt();
            if (i == 9999) {
                stringBuilder.append(randIntResult);
            } else {
                stringBuilder.append(randIntResult).append(",");
            }
        }
        return stringBuilder.toString();
    }
}
