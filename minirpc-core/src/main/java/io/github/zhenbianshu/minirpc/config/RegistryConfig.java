package io.github.zhenbianshu.minirpc.config;

import lombok.Data;

/**
 * @author zbs
 * @date 2019/9/29
 */
@Data
public class RegistryConfig {
    private String id;

    /**
     * 注册服务器地址
     */
    private String address;

    /**
     * 注册服务器端口
     */
    private Integer port;
}
