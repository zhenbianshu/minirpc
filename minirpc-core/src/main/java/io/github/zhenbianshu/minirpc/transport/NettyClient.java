package io.github.zhenbianshu.minirpc.transport;

import io.github.zhenbianshu.minirpc.core.Request;
import io.github.zhenbianshu.minirpc.core.Response;
import io.github.zhenbianshu.minirpc.core.ResponseFuture;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.util.concurrent.CompletableFuture;

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
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                Response response = NettyClient.this.codec.decode(((String) msg).getBytes());

                                CompletableFuture<Object> future = ResponseFuture.REQUEST_TO_FUTURE.remove(response.getRequestId());
                                future.complete(response.getResponseVal());
                            }
                        });
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
            channel = channelFuture.channel();
        } catch (Exception e) {
            // log
        }
    }


    @Override
    public void send(Request request) {
        byte[] data = codec.encode(request);
        channel.writeAndFlush(data);
    }

}
