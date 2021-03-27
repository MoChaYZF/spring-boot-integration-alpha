package top.yangzefeng.integration.dynamic.enums;

import sun.reflect.FieldAccessor;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 描述 : 反射辅助器.
 *
 * @author : MoCha
 * @version : v1
 * @date : 2021-03-27 11:09
 */
public class ReflectionHelper {
    private static final String MODIFIERS_FIELD = "modifiers";

    private static final ReflectionFactory REFLECTION = ReflectionFactory.getReflectionFactory();

    public static void setStaticFinalField(Field field, Object value) throws NoSuchFieldException, IllegalAccessException {
        // 获得 public 权限
        field.setAccessible(true);

        // 将modifiers域设为非final,这样就可以修改了
        Field modifiersField = Field.class.getDeclaredField(MODIFIERS_FIELD);
        modifiersField.setAccessible(true);
        int modifiers = modifiersField.getInt(field);

        // 去掉 final 标志位
        modifiers &= ~Modifier.FINAL;
        modifiersField.setInt(field, modifiers);
        FieldAccessor fieldAccessor = REFLECTION.newFieldAccessor(field, false);
        fieldAccessor.set(null, value);
    }
}
