package io.github.zhenbianshu.minirpc.spring;

import lombok.Data;

/**
 * @author zbs
 * @date 2019/9/13
 */
@Data
public class RpcProviderConfig {
    private String name;
    private Class<?> interfaceName;
    private Object ref;
    private Integer export;
}
