package io.github.zhenbianshu.core;

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
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private Integer serviceId;
    private Long requestId;
    private Object[] parameters;
}
