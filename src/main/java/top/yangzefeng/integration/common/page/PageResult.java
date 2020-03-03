package top.yangzefeng.integration.common.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果数据
 * <p>
 * 说明：以 ElementUI 分页为标准
 *
 * @author MoCha
 * @date 2019/10/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 3385276661767689571L;

    /**
     * 分页记录
     */
    private List<T> rows;

    /**
     * 分页记录总数
     */
    private Long total;

    /**
     * 当前分页总页数
     * <p>
     * 说明：
     * 1. 用于辅助根据总页数来判断是否需要继续进行分页操作
     * 2. 当"当前页数" > "当前分页总页数"时，说明已经没有数据可以再分了，此时可以不再进行分页操作，pageNumber > pages
     */
    private Long pages;

    public PageResult(List<T> rows, Long total) {
        this.rows = rows;
        this.total = total;
    }
}
