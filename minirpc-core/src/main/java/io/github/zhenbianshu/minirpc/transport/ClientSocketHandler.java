package io.github.zhenbianshu.minirpc.transport;

import io.github.zhenbianshu.minirpc.codec.Codec;
import io.github.zhenbianshu.minirpc.core.Response;
import io.github.zhenbianshu.minirpc.core.ResponseFuture;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.CompletableFuture;

/**
 * @author zbs
 * @date 2020/4/2
 */
public class ClientSocketHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private Codec codec;

    ClientSocketHandler(Codec codec) {
        this.codec = codec;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf data) throws Exception {
        Response response = codec.decode(data);

        CompletableFuture<Object> future = ResponseFuture.REQUEST_TO_FUTURE.remove(response.getRequestId());
        future.complete(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
