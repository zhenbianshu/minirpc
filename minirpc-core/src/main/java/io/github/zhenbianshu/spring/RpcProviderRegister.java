package io.github.zhenbianshu.spring;

import javax.annotation.PostConstruct;

/**
 * 读取 {@link RpcProviderConfig} 的配置
 * 向 {@link io.github.zhenbianshu.core.Provider#PROVIDERS_MAP} 内注册服务
 *
 * @author zbs
 * @date 2019/9/13
 */
public class RpcProviderRegister {
    @PostConstruct
    public void initProviders() {

    }
}
