package io.github.zhenbianshu.core;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zbs
 * @date 2019/9/9
 */
@Data
@Builder
public class Provider {
    public static Map<Integer, Provider> PROVIDERS_MAP = new ConcurrentHashMap<>();

    private Integer id;
    private Object classObject;
    private Method method;
}
