package top.yangzefeng.integration.common.util;

import com.alibaba.fastjson.JSONObject;
import top.yangzefeng.integration.common.ResultDTO;
import top.yangzefeng.integration.common.enums.ResultStatusEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应结果工具类
 *
 * @author MoCha
 * @date 2019/10/9
 */
public final class ResultDTOUtils {
    /**
     * 请求成功，无数据返回
     *
     * @param <T> 泛型
     * @return 返回带有请求成功信息以及请求成功码的Result
     */
    public static <T> ResultDTO<T> success() {
        return new ResultDTO<>(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 请求成功，带数据返回
     *
     * @param data 返回的数据
     * @param <T>  泛型
     * @return 返回带有请求成功信息，请求成功码以及数据的Result
     */
    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<>(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 请求成功，自定义提示信息、所返回数据内容
     *
     * @param message 自定义提示信息
     * @param data    返回数据 当不需要数据时，可设置data为null
     * @param <T>     泛型
     * @return 返回带有请求成功信息，请求成功码以及数据的Result
     */
    public static <T> ResultDTO<T> success(String message, T data) {
        return new ResultDTO<>(ResultStatusEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 请求失败，不带数据返回
     *
     * @param <T> 泛型
     * @return 操作结果
     */
    public static <T> ResultDTO<T> fail() {
        return new ResultDTO<>(ResultStatusEnum.INTERNAL_SERVER_ERROR.getCode(), ResultStatusEnum.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

    /**
     * 请求失败，不带数据返回
     *
     * @param errorMessage 自定义错误消息
     * @param <T>          泛型
     * @return 操作结果
     */
    public static <T> ResultDTO<T> fail(String errorMessage) {
        return new ResultDTO<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage, null);
    }

    /**
     * 请求失败，不带数据返回
     *
     * @param code         自定义错误码
     * @param errorMessage 自定义错误消息
     * @param <T>          泛型
     * @return 操作结果
     */
    public static <T> ResultDTO<T> fail(Integer code, String errorMessage) {
        return new ResultDTO<>(code, errorMessage, null);
    }

    /**
     * 请求失败，不带数据返回
     *
     * @param resultStatusEnum 响应状态枚举
     * @param <T>              泛型
     * @return 操作结果
     */
    public static <T> ResultDTO<T> fail(ResultStatusEnum resultStatusEnum) {
        return new ResultDTO<>(resultStatusEnum.getCode(), resultStatusEnum.getMessage(), null);
    }

    /**
     * 设置响应
     *
     * @param response    HttpServletResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param value       响应内容
     * @throws IOException IOException
     */
    public static void makeResponse(HttpServletResponse response, String contentType,
                                    int status, Object value) throws IOException {
        response.setContentType(contentType);
        response.setStatus(status);
        response.getOutputStream()
                .write(JSONObject.toJSONString(value).getBytes());
    }

    /**
     * 阻止实例化
     * <p>
     * Prevents instantiation
     */
    private ResultDTOUtils() {
    }
}
