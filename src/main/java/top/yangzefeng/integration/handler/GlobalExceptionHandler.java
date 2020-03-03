package top.yangzefeng.integration.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.yangzefeng.integration.common.ResultDTO;
import top.yangzefeng.integration.common.exception.RateLimitException;
import top.yangzefeng.integration.common.util.ResultDTOUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理
 * <p>
 * 说明：
 * 1. 微服务系统独有的异常类型捕获可以通过继承GlobalExceptionHandler的方式来实现自定义异常捕获
 * 2. 通过@Order(value = Ordered.HIGHEST_PRECEDENCE)指定顺序
 *
 * @author MoCha
 * @date 2019/4/2
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    /**
     * 对接口限流异常类进行处理
     *
     * @param exception 接口限流异常类
     * @return 异常结果信息
     */
    @ExceptionHandler(RateLimitException.class)
    public ResultDTO handleException(RateLimitException exception) {
        return ResultDTOUtils.fail(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     * <p>
     * 说明：
     * 当普通类型参数校验不合法时，控制器层会抛出javax.validation.ConstraintViolationException异常
     *
     * @param e ConstraintViolationException
     * @return ResultDTO
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDTO handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return ResultDTOUtils.fail(HttpServletResponse.SC_BAD_REQUEST, message.toString());
    }

    /**
     * 统一处理请求参数校验
     * <p>
     * 说明：
     * 1. 实体对象传参
     * 2. 不使用@RequestBody情况下
     * 3. 使用@Valid进行校验
     * 4. 当校验不通过时，控制器层将抛出BindException类型异常
     *
     * @param e BindException
     * @return ResultDTO
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDTO handleBindException(BindException e) {
        return getErrorValidationMessages(e.getBindingResult());
    }

    /**
     * 统一处理请求参数校验
     * <p>
     * 说明：
     * 1. 实体对象传参
     * 2. 使用@RequestBody情况下
     * 3. 使用@Valid进行校验
     * 4. 当校验不通过时，控制器层将抛出MethodArgumentNotValidException类型异常
     *
     * @param e MethodArgumentNotValidException
     * @return ResultDTO
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDTO exception(MethodArgumentNotValidException e) {
        return getErrorValidationMessages(e.getBindingResult());
    }

    /**
     * 获取实体对象作为参数时的校验信息
     */
    private ResultDTO getErrorValidationMessages(BindingResult bindingResult) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return ResultDTOUtils.fail(HttpServletResponse.SC_BAD_REQUEST, message.toString());
    }
}
