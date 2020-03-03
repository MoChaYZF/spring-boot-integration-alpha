package top.yangzefeng.integration.common.page;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询参数
 * <p>
 * 用于接收前端传递的分页条件和排序条件
 * <p>
 * 说明：以 Element-UI 分页为标准
 *
 * @author MoCha
 * @date 2019/10/15
 */
@Data
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 1118497887142452405L;

    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序规则
     * <p>
     * ascending升序
     * descending降序
     */
    private String order;

    /**
     * 当前页数
     */
    private int pageNum = 1;

    /**
     * 每页限制展示条数
     */
    private int pageSize = 10;
}
