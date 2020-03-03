package top.yangzefeng.integration.common.factory;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yangzefeng.integration.common.util.WebUtils;
import top.yangzefeng.integration.common.util.page.PageUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 分页对象构造工厂
 * <p>
 * 说明：
 * 1、采用静态工厂方法模式，把分页对象创建的逻辑交给了工厂，调用者无需关注工厂底层是如何创建的，只需要关心，工厂能产生什么。
 * 2、只需要定义好工厂方法的入参和出参，就能对外隐藏其内部的创建逻辑，提供更加友好方便的使用体验。
 *
 * @author MoCha
 * @date 2019/10/17
 */
public final class PageFactory {
    /**
     * 构造默认Page对象
     * <p>
     * 说明：
     * T 代表泛型，表示当前返回的泛型类型和声明的一致即可，在编译的时候，会把泛型 T 转化成我们声明的类型
     *
     * @return Page分页对象
     */
    public static <T> Page<T> defaultPage() {
        HttpServletRequest request = WebUtils.getRequest();
        // TODO 根据需求，将request.getParameter("pageNum")和request.getParameter("pageSize")进行参数名的替换
        // 当前页码
        long pageNumber = Long.parseLong(Optional.ofNullable(request.getParameter("pageNum")).orElseGet(() -> String.valueOf(0)));
        // 每页显示条数
        long pageSize = Long.parseLong(Optional.ofNullable(request.getParameter("pageSize")).orElseGet(() -> String.valueOf(0)));

        // 当没有传递limit或者offset
        // 默认第一页，展示10条数据
        return createPage(pageNumber, pageSize);
    }

    /**
     * 构造分页page对象
     * <p>
     * 说明：
     * 1、当pageNumber、pageSize的值不合法时，默认展示第一页，10条数据
     * 2、T 代表泛型，表示当前返回的泛型类型和声明的一致即可，在编译的时候，会把泛型 T 转化成我们声明的类型
     *
     * @param pageNumber 当前分页数
     * @param pageSize   每页展示条数
     * @return page分页对象
     */
    public static <T> Page<T> createPage(long pageNumber, long pageSize) {
        Page<T> page = new Page<T>();
        PageUtils.rationalizePage(pageNumber, pageSize, page);
        return page;
    }

    /**
     * 阻止实例化
     * <p>
     * Prevents instantiation
     */
    private PageFactory() {
    }
}
