package io.github.zhenbianshu.minirpc.config;

import lombok.Data;

import java.util.List;

/**
 * @author zbs
 * @date 2019/9/29
 */
@Data
public class ProviderConfig<T> {
    private String host;

    private Integer export;

    private List<MethodConfig> methods;

    private RegistryConfig registryConfig;

    private T ref;

    private Class<T> interfaceName;
}
