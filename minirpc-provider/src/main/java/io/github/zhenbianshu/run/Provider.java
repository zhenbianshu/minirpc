package io.github.zhenbianshu.run;

import io.github.zhenbianshu.demo.HelloService;
import io.github.zhenbianshu.model.Service;
import io.github.zhenbianshu.model.ServiceMapping;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author zbs
 * @date 2019/9/3
 */
@Slf4j
public class Provider {
    public static void main(String[] args) {
        initService();

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 16);
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline p = socketChannel.pipeline();
                    p.addLast(new StringDecoder());
                    p.addLast(new SocketHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.bind(8122).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("start server error", e);
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static void initService() {
        HelloService helloService = new HelloService();

        Method method = null;
        try {
            method = HelloService.class.getDeclaredMethod("say", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Service service = Service.builder()
                .classObject(helloService)
                .id(666)
                .method(method)
                .build();

        ServiceMapping.SERVICE_MAP.put(666, service);
    }
}
