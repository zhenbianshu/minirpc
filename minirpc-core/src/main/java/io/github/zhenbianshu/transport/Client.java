package io.github.zhenbianshu.transport;

import com.alibaba.fastjson.JSON;
import io.github.zhenbianshu.core.ResponseFuture;
import io.github.zhenbianshu.core.Request;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
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
public class Client {
    private String ip;
    private Integer port;
    private Channel channel;

    public Client(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public boolean connect() {
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
                        ch.pipeline().addLast(new ClientSocketHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
            channel = channelFuture.channel();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public CompletableFuture<Object> send(Request request) {
        channel.writeAndFlush(Unpooled.copiedBuffer(JSON.toJSONBytes(request)));
        return new ResponseFuture(request);
    }

}
