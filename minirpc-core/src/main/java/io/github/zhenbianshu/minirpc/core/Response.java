package io.github.zhenbianshu.minirpc.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zbs
 * @date 2019/9/5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Long requestId;
    private Object responseVal;
    private Integer errorCode;
}
