package top.yangzefeng.integration.carousel.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 轮播图数据表
 *
 * @author MoCha
 * @date 2020/03/03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_carousel")
public class Carousel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 轮播图主键编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 轮播图图片地址
     */
    private String image;

    /**
     * 轮播图排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     * <p>
     * 说明：需要手动设置，否则即便数据库表指定了该列自动更新，也无效
     */
    @TableField(value = "modify_time", update = "now()")
    private LocalDateTime modifyTime;
}
