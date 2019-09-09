package io.github.zhenbianshu.core;

import lombok.Builder;
import lombok.Data;

/**
 * @author zbs
 * @date 2019/9/5
 */
@Data
@Builder
public class Response {
    private Long requestId;
    private Object responseVal;
    private Integer errorCode;
}
