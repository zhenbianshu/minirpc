package io.github.zhenbianshu.minirpc.core;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zbs
 * @date 2019/9/9
 */
@Data
public class Provider<T> {
    public static Map<String, Provider<?>> PROVIDERS_MAP = new ConcurrentHashMap<>();

    private Map<String, Method> methodMap = new HashMap<>();

    private Class<T> serviceClass;
    private T classObject;

    public Provider(Class<T> serviceClass, T classObject) {
        this.serviceClass = serviceClass;
        this.classObject = classObject;
        init();
    }

    public Method getMethod(String methodName) {
        return methodMap.get(methodName);
    }

    private void init() {
        Method[] methods = serviceClass.getDeclaredMethods();
        for (Method method : methods) {
            methodMap.put(method.getName(), method);
        }
    }
}
