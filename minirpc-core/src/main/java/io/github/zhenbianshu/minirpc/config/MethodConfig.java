package io.github.zhenbianshu.minirpc.config;

import lombok.Data;

/**
 * @author zbs
 * @date 2019/9/30
 */
@Data
public class MethodConfig {
    private String name;

    private String argumentTypes;
}
