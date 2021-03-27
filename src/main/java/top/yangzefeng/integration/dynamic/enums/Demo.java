package top.yangzefeng.integration.dynamic.enums;

import top.yangzefeng.integration.common.constant.DemoEnumSkip;

import java.util.Arrays;

/**
 * 描述 : .
 *
 * @author : MoCha
 * @version : v1
 * @date : 2021-03-27 11:18
 */
public class Demo {
    public static void main(String[] args) throws NoSuchMethodException {
        EnumBuster<DemoEnumSkip> demoEnumSkipEnumBuster = new EnumBuster<>(DemoEnumSkip.class);
        // String, int是Enum构造方法固定前两个入参
        // make第三、四个入参为我们自定义扩展的字段类型
        DemoEnumSkip unDoEnum = demoEnumSkipEnumBuster.make("UN_DO", 10, new Class[]{String.class, String.class}, new Object[]{"100", "UN_TEST"});
        demoEnumSkipEnumBuster.addByValue(unDoEnum);

        System.out.println(Arrays.toString(DemoEnumSkip.values()));

        System.out.println("===枚举名称====");
        for (DemoEnumSkip demoEnum : DemoEnumSkip.values()) {
            System.out.println(demoEnum.getName() + " = " + demoEnum.getCode());
        }

        DemoEnumSkip.setExtension("100", unDoEnum);

        System.out.println(DemoEnumSkip.getPreviewOfficeEnum("100"));
    }
}
