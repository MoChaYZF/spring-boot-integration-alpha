package top.yangzefeng.integration.carousel.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yangzefeng.integration.carousel.dto.CarouselDTO;
import top.yangzefeng.integration.carousel.service.CarouselService;
import top.yangzefeng.integration.common.ResultDTO;
import top.yangzefeng.integration.common.page.PageQuery;
import top.yangzefeng.integration.common.page.PageResult;
import top.yangzefeng.integration.common.util.ResultDTOUtils;

/**
 * 轮播图 请求控制器
 *
 * @author MoCha
 * @date 2020/03/03
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/carousel")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarouselController {
    private final CarouselService carouselService;

    /**
     * 获取轮播图分页数据
     *
     * @param carouselDTO 轮播图 传输对象
     * @return 轮播图分页数据
     */
    @GetMapping
    public ResultDTO carouselList(CarouselDTO carouselDTO, PageQuery pageQuery) {
        PageResult<CarouselDTO> bonusFlowsDtoPage = carouselService.findCarouselDetailPage(carouselDTO, pageQuery);
        return ResultDTOUtils.success(bonusFlowsDtoPage);
    }
}

