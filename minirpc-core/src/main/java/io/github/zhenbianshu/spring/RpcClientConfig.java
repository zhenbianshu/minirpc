package io.github.zhenbianshu.spring;

import lombok.Data;

/**
 * @author zbs
 * @date 2019/9/13
 */
@Data
public class RpcClientConfig {
    private String name;
    private Class<?> interfaceName;
    private String host;
    private Integer port;
}
