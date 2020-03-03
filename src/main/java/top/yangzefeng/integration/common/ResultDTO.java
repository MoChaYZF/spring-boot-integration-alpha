package top.yangzefeng.integration.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应结果实体类
 *
 * @author MoCha
 * @date 2019/4/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO<T> implements Serializable {
    private static final long serialVersionUID = -8701856959222041695L;

    private Integer code;
    private String message;
    private T data;
}
