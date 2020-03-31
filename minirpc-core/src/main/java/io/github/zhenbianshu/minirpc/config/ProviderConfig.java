package io.github.zhenbianshu.minirpc.config;

import lombok.Data;

import java.util.List;

/**
 * 服务配置
 *
 * @author zbs
 * @date 2019/9/29
 */
@Data
public class ProviderConfig<T> {
    private String id;

    /**
     * 暴露的端口
     */
    private Integer export;

    /**
     * 暴露的接口方法
     */
    private List<MethodConfig> methods;

    /**
     * 注册服务器配置
     */
    private RegistryConfig registry;

    /**
     * 服务实例
     */
    private T ref;
}
