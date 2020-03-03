package top.yangzefeng.integration.common.constant;

/**
 * 分页 常量类
 * <p>
 * 说明：以 ElementUI 分页为标准
 *
 * @author MoCha
 * @date 2019/10/15
 */
public final class PageConstants {
    /**
     * 每页最大查询条数
     */
    public static final Long MAX_LIMIT = 100L;

    /**
     * 分页数据
     */
    public static final String ROWS = "rows";

    /**
     * 分页总条数
     */
    public static final String TOTAL = "total";

    /**
     * 降序排序
     */
    public static final String ORDER_DESC = "descending";

    /**
     * 升序排序
     */
    public static final String ORDER_ASC = "ascending";

    /**
     * 阻止实例化
     * <p>
     * Prevents instantiation
     */
    private PageConstants() {
    }
}
