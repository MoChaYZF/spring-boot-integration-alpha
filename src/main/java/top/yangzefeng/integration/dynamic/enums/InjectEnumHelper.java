package top.yangzefeng.integration.dynamic.enums;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import top.yangzefeng.integration.common.constant.DemoEnumSkip;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 描述 : .
 *
 * @author : MoCha
 * @version : v1
 * @date : 2021-03-27 15:11
 */
@Component
public class InjectEnumHelper {
    private static Properties props;

    static {
        String fileName = "module.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(InjectEnumHelper.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
            Enumeration<?> enumeration = props.propertyNames();
            while (enumeration.hasMoreElements()) {
                Object element = enumeration.nextElement();
                System.out.println(element);

                String property = props.getProperty((String) element);
                String enumName = StringUtils.substringAfter((String) element, ".");

                EnumBuster<DemoEnumSkip> demoEnumSkipEnumBuster = new EnumBuster<>(DemoEnumSkip.class);
                // String, int是Enum构造方法固定前两个入参
                // make第三、四个入参为我们自定义扩展的字段类型
                DemoEnumSkip unDoEnum = demoEnumSkipEnumBuster.make(StringUtils.upperCase(enumName), DemoEnumSkip.values().length, new Class[]{String.class, String.class}, new Object[]{enumName, property});
                demoEnumSkipEnumBuster.addByValue(unDoEnum);
                DemoEnumSkip.setExtension(property, unDoEnum);
            }
        } catch (IOException e) {
        }
    }
}
