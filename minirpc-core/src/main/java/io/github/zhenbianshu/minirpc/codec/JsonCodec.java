package io.github.zhenbianshu.minirpc.codec;

import com.alibaba.fastjson.JSON;
import io.github.zhenbianshu.minirpc.core.Request;
import io.github.zhenbianshu.minirpc.core.Response;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author zbs
 * @date 2019/12/13
 */
public class JsonCodec implements Codec {
    @Override
    public ByteBuf encode(Request request) {
        String json = JSON.toJSONString(request);
        return Unpooled.copiedBuffer(json.getBytes());
    }

    @Override
    public Response decode(byte[] data) {
        return JSON.parseObject(data, Response.class);
    }
}
