package io.github.zhenbianshu.minirpc.codec;

import io.github.zhenbianshu.minirpc.core.Request;
import io.github.zhenbianshu.minirpc.core.Response;
import io.netty.buffer.ByteBuf;

/**
 * @author zbs
 * @date 2019/12/13
 */
public interface Codec {
    ByteBuf encode(Request request);

    Response decode(byte[] data);
}
