package top.yangzefeng.integration.common.constant;

/**
 * Base64字符串类型常量
 *
 * @author MoCha
 * @date 2019/12/25
 */
public final class Base64Constants {
    /**
     * 分隔base64数组的大小
     */
    public static final int BASE64_ARRAY_DETAIL_LENGTH = 2;

    /**
     * JPG
     */
    public static final String JPG = "data:image/jpeg;";

    /**
     * ICON
     */
    public static final String ICON = "data:image/x-icon;";

    /**
     * GIF
     */
    public static final String GIF = "data:image/gif;";

    /**
     * PNG
     */
    public static final String PNG = "data:image/png;";

    /**
     * 阻止实例化
     * <p>
     * Prevents instantiation
     */
    private Base64Constants() {
    }
}
