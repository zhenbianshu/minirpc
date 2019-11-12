package io.github.zhenbianshu.minirpc.proxy;

import io.github.zhenbianshu.minirpc.core.Cluster;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zbs
 * @date 2019/11/12
 */
public class RefererInvocationHandler<T> implements InvocationHandler {
    private Class<T> refererClass;

    private List<Cluster> clusters;

    public RefererInvocationHandler(Class<T> refererClass, List<Cluster> clusters) {
        this.refererClass = refererClass;
        this.clusters = clusters;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // cluster.call

        return null;
    }
}
