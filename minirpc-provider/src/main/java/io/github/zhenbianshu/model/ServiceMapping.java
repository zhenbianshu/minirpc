package io.github.zhenbianshu.model;

import io.github.zhenbianshu.model.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zbs
 * @date 2019/9/9
 */
public class ServiceMapping {
    public static Map<Integer, Service> SERVICE_MAP = new ConcurrentHashMap<>();
}
