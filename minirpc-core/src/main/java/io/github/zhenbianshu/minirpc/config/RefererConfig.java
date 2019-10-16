package io.github.zhenbianshu.minirpc.config;

import lombok.Data;

/**
 * @author zbs
 * @date 2019/9/29
 */
@Data
public class RefererConfig<T> {
    private String id;

    private Class<T> serviceInterface;

    private RegistryConfig registry;

    private String directUrl;
}
