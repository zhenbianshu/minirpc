package io.github.zhenbianshu.minirpc.config;

import lombok.Data;

import java.util.List;

/**
 * @author zbs
 * @date 2019/9/29
 */
@Data
public class ProviderConfig<T> {
    private String id;

    private Integer export;

    private List<MethodConfig> methods;

    private RegistryConfig registry;

    private T ref;
}
