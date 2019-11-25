package io.github.zhenbianshu.minirpc.proxy;

import io.github.zhenbianshu.minirpc.core.Request;
import io.github.zhenbianshu.minirpc.core.exception.ErrorCode;
import io.github.zhenbianshu.minirpc.core.exception.RpcException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Random;

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
        if (method.getDeclaringClass().equals(Object.class)) {
            if ("toString".equals(method.getName())) {
                return remote.toString();
            }

            throw new RpcException("local method not supported", ErrorCode.PARAM);
        }

        Request request = Request.builder()
                .requestId(new Random().nextLong())
                .className(refererClass.getName())
                .methodName(method.getName())
                .parameters(args)
                .build();

        return remote.call(request);
    }
}
