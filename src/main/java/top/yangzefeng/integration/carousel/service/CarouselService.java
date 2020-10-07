package top.yangzefeng.integration.carousel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yangzefeng.integration.carousel.domain.dto.CarouselDTO;
import top.yangzefeng.integration.carousel.domain.entity.Carousel;
import top.yangzefeng.integration.common.page.PageQuery;
import top.yangzefeng.integration.common.page.PageResult;

/**
 * 轮播图 业务层接口
 *
 * @author MoCha
 * @date 2020/03/03
 */
public interface CarouselService extends IService<Carousel> {
    /**
     * 获取轮播图分页数据
     *
     * @param carouselDTO 轮播图 传输对象
     * @param pageQuery   分页查询参数
     * @return 轮播图分页数据
     */
    PageResult<CarouselDTO> findCarouselDetailPage(CarouselDTO carouselDTO, PageQuery pageQuery);
}
