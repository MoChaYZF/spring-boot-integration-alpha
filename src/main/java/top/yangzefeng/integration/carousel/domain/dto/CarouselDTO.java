package top.yangzefeng.integration.carousel.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import top.yangzefeng.integration.carousel.domain.entity.Carousel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 轮播图 传输对象
 *
 * @author MoCha
 * @date 2020/3/3
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarouselDTO implements Serializable {
    /**
     * 轮播图主键编号
     */
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

    @JsonIgnore
    private String createTimeFrom;

    @JsonIgnore
    private String createTimeTo;

    public CarouselDTO convertCarouselDTO(Carousel carousel) {
        BeanUtils.copyProperties(carousel, this);
        return this;
    }
}
