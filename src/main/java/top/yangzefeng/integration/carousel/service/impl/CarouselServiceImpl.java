package top.yangzefeng.integration.carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yangzefeng.integration.carousel.domain.dto.CarouselDTO;
import top.yangzefeng.integration.carousel.domain.entity.Carousel;
import top.yangzefeng.integration.carousel.mapper.CarouselMapper;
import top.yangzefeng.integration.carousel.service.CarouselService;
import top.yangzefeng.integration.common.page.PageQuery;
import top.yangzefeng.integration.common.page.PageResult;
import top.yangzefeng.integration.common.util.page.PageSortUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图 业务层实现类
 *
 * @author MoCha
 * @date 2020/03/03
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {
    /**
     * 获取轮播图分页数据
     *
     * @param carouselDTO 轮播图 传输对象
     * @param pageQuery   分页查询参数
     * @return 轮播图分页数据
     */
    @Override
    public PageResult<CarouselDTO> findCarouselDetailPage(CarouselDTO carouselDTO, PageQuery pageQuery) {
        LambdaQueryWrapper<Carousel> lambdaQueryWrapper = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(carouselDTO.getCreateTimeFrom())
                && StringUtils.isNotBlank(carouselDTO.getCreateTimeTo())) {
            lambdaQueryWrapper
                    .ge(Carousel::getCreateTime, carouselDTO.getCreateTimeFrom())
                    .le(Carousel::getCreateTime, carouselDTO.getCreateTimeTo());
        }

        Page<Carousel> page = new Page<>();
        PageSortUtils.handlePageSort(pageQuery, page, new OrderItem[]{OrderItem.desc("create_time")}, true);

        List<Carousel> carouselList = this.page(page, lambdaQueryWrapper).getRecords();

        // 转换
        List<CarouselDTO> carouselDTOList = new ArrayList<>();
        carouselList.forEach(carousel -> carouselDTOList.add(new CarouselDTO().convertCarouselDTO(carousel)));

        return new PageResult<>(carouselDTOList, page.getTotal());
    }
}
