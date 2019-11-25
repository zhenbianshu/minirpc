package io.github.zhenbianshu.minirpc.serialize;

import com.alibaba.fastjson.JSON;
import io.github.zhenbianshu.minirpc.core.Request;

/**
 * @author zbs
 * @date 2019/9/13
 */
public class FastJsonSerialize implements Serialization {
    @Override
    public byte[] serialize(Request request) {
        return JSON.toJSONString(request).getBytes();
    }
}
