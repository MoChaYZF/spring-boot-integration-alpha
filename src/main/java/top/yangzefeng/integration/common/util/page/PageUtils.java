package top.yangzefeng.integration.common.util.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yangzefeng.integration.common.constant.PageConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页工具类
 *
 * @author MoCha
 * @date 2019/11/19
 */
public final class PageUtils {
    /**
     * 封装前端分页表格所需数据
     *
     * @param pageInfo pageInfo
     * @return Map<String, Object>
     */
    public static <T> Map<String, Object> getDataTable(IPage<T> pageInfo) {
        Map<String, Object> data = new HashMap<>(2);
        data.put("rows", pageInfo.getRecords());
        data.put("total", pageInfo.getTotal());
        return data;
    }

    /**
     * 合理化Page分页对象的当前分页数/每页展示条数
     * <p>
     * 当pageNumber、pageSize的值不合法时，默认展示第一页，10条数据
     *
     * @param pageNumber 当前分页数
     * @param pageSize   每页展示条数
     * @param page       分页对象
     */
    public static <T> void rationalizePage(long pageNumber, long pageSize, Page<T> page) {
        // 为了防止一次性查询过大数据导致卡死状态
        if (pageSize > PageConstants.MAX_LIMIT) {
            pageSize = PageConstants.MAX_LIMIT;
        }

        if (pageSize > 0 && pageNumber > 0) {
            page.setSize(pageSize);
            page.setCurrent(pageNumber);
        } else {
            // 当分页条件不合理时，设置默认第一页/展示10条数据
            // 根据实际需求进行调整
            page.setCurrent(1);
            page.setSize(10);
        }
    }

    /**
     * 阻止实例化
     * <p>
     * Prevents instantiation
     */
    private PageUtils() {
    }
}
