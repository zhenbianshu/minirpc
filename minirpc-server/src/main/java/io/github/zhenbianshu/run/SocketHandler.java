package io.github.zhenbianshu.run;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zbs
 * @date 2019/9/3
 */
@Slf4j
public class SocketHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString());

        try {
            ctx.writeAndFlush("return");
        } catch (Exception e) {
            log.warn("response error, content:{}", e);
        }
    }
}
