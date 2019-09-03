package io.github.zhenbianshu.run;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zbs
 * @date 2019/9/3
 */
@Slf4j
public class Acceptor {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 16);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);


        try {
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline p = socketChannel.pipeline();
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
}
