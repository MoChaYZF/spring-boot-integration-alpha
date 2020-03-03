package top.yangzefeng.integration.carousel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yangzefeng.integration.common.ResultDTO;
import top.yangzefeng.integration.common.util.ResultDTOUtils;
import top.yangzefeng.integration.common.util.UserAgentUtils;

/**
 * 测试浏览器用户代理
 *
 * @author MoCha
 * @date 2019/12/19
 */
@Slf4j
@RestController
@RequestMapping("/ua")
public class TestUserAgentController {
    /**
     * 获取浏览器用户代理信息
     */
    @GetMapping
    public ResultDTO testUa() {
        return ResultDTOUtils.success(UserAgentUtils.getUserAgentInfo());
    }
}
