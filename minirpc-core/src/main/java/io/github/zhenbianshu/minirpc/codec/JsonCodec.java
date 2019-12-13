package io.github.zhenbianshu.minirpc.codec;

import com.alibaba.fastjson.JSON;
import io.github.zhenbianshu.minirpc.core.Request;
import io.github.zhenbianshu.minirpc.core.Response;

/**
 * @author zbs
 * @date 2019/12/13
 */
public class JsonCodec implements Codec {
    @Override
    public byte[] encode(Request request) {
        return JSON.toJSONBytes(request);
    }

    @Override
    public Response decode(byte[] data) {
        return JSON.parseObject(data, Response.class);
    }
}
