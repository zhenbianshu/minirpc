package io.github.zhenbianshu.minirpc.codec;

import io.github.zhenbianshu.minirpc.core.Request;
import io.github.zhenbianshu.minirpc.core.Response;

/**
 * @author zbs
 * @date 2019/12/13
 */
public interface Codec {
    byte[] encode(Request request);

    Response decode(byte[] data);
}
