package io.github.zhenbianshu.minirpc.config;

import io.github.zhenbianshu.minirpc.core.Cluster;
import io.github.zhenbianshu.minirpc.proxy.RefererInvocationHandler;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

/**
 * @author zbs
 * @date 2019/9/29
 */
@Data
public class RefererConfig<T> implements FactoryBean<T> {
    private String id;

    private Class<T> serviceInterface;

    private RegistryConfig registry;

    private String directUrl;

    @Override
    public T getObject() throws Exception {
        return getRef();
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    public T getRef() {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), serviceInterface.getClasses(), new RefererInvocationHandler<T>(serviceInterface, getClusters(serviceInterface)));

    }

    private List<Cluster> getClusters(Class<T> serviceInterface) {
        //  get Clusters from registry or directUrl
        return Collections.emptyList();
    }
}
