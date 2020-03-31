package io.github.zhenbianshu.minirpc.config;

import io.github.zhenbianshu.minirpc.proxy.RefererInvocationHandler;
import io.github.zhenbianshu.minirpc.proxy.Remote;
import io.github.zhenbianshu.minirpc.proxy.ServerNode;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

import java.lang.reflect.Proxy;

/**
 * @author zbs
 * @date 2019/9/29
 */
@Data
public class RefererConfig<T> implements FactoryBean<T> {
    private String id;

    /**
     * 服务接口
     */
    private Class<T> serviceInterface;

    /**
     * 注册服务器地址，用以获取服务器地址
     */
    private RegistryConfig registry;

    /**
     * 直连时的服务器地址
     */
    private String directUrl;

    @Override
    public T getObject() throws Exception {
        return getRef();
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    private T getRef() {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, new RefererInvocationHandler<>(serviceInterface, getRemote()));

    }

    private Remote getRemote() {
        if (!StringUtils.isEmpty(directUrl)) {
            return new ServerNode(directUrl);
        }
        //  get Clusters from registry or directUrl
        return null;
    }
}
