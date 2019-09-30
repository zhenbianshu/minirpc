package io.github.zhenbianshu.minirpc.config;

import lombok.Data;

/**
 * @author zbs
 * @date 2019/9/29
 */
@Data
public class RegistryConfig {
    private String id;

    private String address;

    private Integer port;
}
