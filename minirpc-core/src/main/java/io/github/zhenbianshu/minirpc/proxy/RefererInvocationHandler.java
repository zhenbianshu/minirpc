package io.github.zhenbianshu.minirpc.proxy;

import io.github.zhenbianshu.minirpc.core.Request;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zbs
 * @date 2019/11/12
 */
public class RefererInvocationHandler<T> implements InvocationHandler {
    private Class<T> refererClass;

    private Remote remote;

    public RefererInvocationHandler(Class<T> refererClass, Remote remote) {
        this.refererClass = refererClass;
        this.remote = remote;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = Request.builder().className(refererClass.getName()).methodName(method.getName()).parameters(args).build();

        return remote.call(request);
    }
}
