package top.yangzefeng.integration.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应结果状态枚举类
 *
 * @author MoCha
 * @date 2019/4/2
 */
@Getter
@AllArgsConstructor
public enum ResultStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功！"),

    /**
     * 服务器异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器睡着了！"),

    /**
     * 文件上传失败
     */
    UPLOAD_ERROR(500, "文件上传异常！"),

    /**
     * 无效文件内容
     */
    INVALID_UPLOAD_CONTENT(500, "无效文件内容！");

    private final Integer code;

    private final String message;
}
