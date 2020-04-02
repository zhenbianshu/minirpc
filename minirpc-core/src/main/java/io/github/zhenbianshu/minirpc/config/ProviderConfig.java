package io.github.zhenbianshu.minirpc.config;

import io.github.zhenbianshu.minirpc.core.Provider;
import io.github.zhenbianshu.minirpc.transport.NettyServer;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * 服务配置
 *
 * @author zbs
 * @date 2019/9/29
 */
@Data
public class ProviderConfig<T> implements InitializingBean {
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
     * 服务实例实现的接口
     */
    private Class<T> serviceInterface;

    /**
     * 服务实例
     */
    private T ref;

    @Override
    public void afterPropertiesSet() throws Exception {
        NettyServer server = new NettyServer();
        server.init(export);
        Provider<?> provider = new Provider<>(serviceInterface, ref);
        Provider.PROVIDERS_MAP.put(serviceInterface.getName(), provider);
    }
}
