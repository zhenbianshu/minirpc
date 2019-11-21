package io.github.zhenbianshu.minirpc.serialize;

import io.github.zhenbianshu.minirpc.core.Request;

/**
 * @author zbs
 * @date 2019/9/13
 */
public interface Serialization {
    String serialize(Request request);
}
