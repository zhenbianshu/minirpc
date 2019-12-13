package io.github.zhenbianshu.minirpc.transport;

import io.github.zhenbianshu.minirpc.codec.Codec;
import io.github.zhenbianshu.minirpc.codec.JsonCodec;
import io.github.zhenbianshu.minirpc.core.Request;

/**
 * @author zbs
 * @date 2019/12/13
 */
public abstract class Client {
    protected Codec codec = new JsonCodec();

    public abstract void send(Request request);
}
