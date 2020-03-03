package top.yangzefeng.integration.common.util.page;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import top.yangzefeng.integration.common.constant.PageConstants;
import top.yangzefeng.integration.common.page.PageQuery;


/**
 * 分页排序工具类
 *
 * @author MoCha
 * @date 2020/1/6
 */
public final class PageSortUtils {
    /**
     * 根据分页查询参数进行Page分页对象相关参数设置
     *
     * @param pageQuery 分页查询参数
     * @param page      Page分页对象
     */
    public static <T> void handlePageSort(PageQuery pageQuery, Page<T> page, boolean toUnderlineCase) {
        handlePageSort(pageQuery, page, null, toUnderlineCase);
    }

    /**
     * 根据分页查询参数进行Page分页对象相关参数设置
     *
     * @param pageQuery  分页查询参数
     * @param page       Page分页对象
     * @param orderItems 排序对象，以数据表列名作为参考
     */
    public static <T> void handlePageSort(PageQuery pageQuery, Page<T> page, OrderItem[] orderItems, boolean toUnderlineCase) {
        long pageNumber = pageQuery.getPageNum();
        long pageSize = pageQuery.getPageSize();

        // 合理化Page分页对象相关参数
        PageUtils.rationalizePage(pageNumber, pageSize, page);

        // 获取排序字段
        String sortField = pageQuery.getField();
        // 获取排序字段的排序方式
        String order = pageQuery.getOrder();
        if (toUnderlineCase) {
            // 将驼峰式命名的字符串转换为下划线
            sortField = StrUtil.toUnderlineCase(sortField);
            order = StrUtil.toUnderlineCase(order);
        }

        // 如果存在需要排序的字段，进行排序顺序设置
        boolean hasSortField = StringUtils.isNotBlank(sortField)
                && StringUtils.isNotBlank(order)
                && !StringUtils.equalsIgnoreCase(sortField, "null")
                && !StringUtils.equalsIgnoreCase(order, "null");

        if (hasSortField) {
            // 如果需要设置分页字段排序规则为升序
            if (PageConstants.ORDER_ASC.equals(order)) {
                page.addOrder(OrderItem.asc(sortField));
            } else {
                // 降序排序
                page.addOrder(OrderItem.desc(sortField));
            }
        }

        // 如果有需要设置默认排序的字段，也进行排序
        if (orderItems != null && orderItems.length > 0) {
            page.addOrder(orderItems);
        }
    }

    /**
     * 阻止实例化
     * <p>
     * Prevents instantiation
     */
    private PageSortUtils() {
    }
}
