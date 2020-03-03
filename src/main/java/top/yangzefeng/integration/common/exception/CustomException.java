package top.yangzefeng.integration.common.exception;

import lombok.Getter;
import top.yangzefeng.integration.common.enums.ResultStatusEnum;

/**
 * 自定义异常类
 *
 * @author MoCha
 * @date 2019/4/2
 */
@Getter
public class CustomException extends RuntimeException {
    private ResultStatusEnum resultStatusEnum;

    public CustomException(ResultStatusEnum resultStatusEnum) {
        this.resultStatusEnum = resultStatusEnum;
    }
}
