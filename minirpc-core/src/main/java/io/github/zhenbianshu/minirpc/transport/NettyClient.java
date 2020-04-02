package io.github.zhenbianshu.minirpc.transport;

import io.github.zhenbianshu.minirpc.core.Request;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zbs
 * @date 2019/9/5
 */
public class NettyClient extends Client {
    private String ip;
    private Integer port;
    private Channel channel;

    public NettyClient(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
        connect();
    }

    private void connect() {
        EventLoopGroup boss = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.option(ChannelOption.SO_BACKLOG, 16);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);

        bootstrap.group(boss)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new ClientSocketHandler(NettyClient.this.codec));
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
            if (channelFuture.isSuccess()) {
                channel = channelFuture.channel();
            }
        } catch (Exception e) {
            // log
        }
    }


    @Override
    public void send(Request request) {
        ByteBuf data = codec.encode(request);
        channel.writeAndFlush(data);
    }

}
