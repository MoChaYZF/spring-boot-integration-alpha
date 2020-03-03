package top.yangzefeng.integration.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用于返回错误响应
 *
 * @author MoCha
 * @date 2019/10/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResultDTO implements Serializable {
    private static final long serialVersionUID = 2411293048557787816L;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 错误消息
     */
    private String msg;
}
