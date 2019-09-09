package io.github.zhenbianshu.run;

import com.alibaba.fastjson.JSON;
import io.github.zhenbianshu.model.Service;
import io.github.zhenbianshu.core.Request;
import io.github.zhenbianshu.core.Response;
import io.github.zhenbianshu.model.ServiceMapping;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author zbs
 * @date 2019/9/3
 */
@Slf4j
public class SocketHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String body = (String) msg;

        System.out.println(msg);

        Request request = JSON.parseObject(body, Request.class);
        Service service = ServiceMapping.SERVICE_MAP.get(request.getServiceId());

        Method method = service.getMethod();
        Object object = service.getClassObject();
        Object[] parameters = request.getParameters();

        Object val = null;
        try {
            val = method.invoke(object, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Response response = Response.builder()
                .responseVal(val)
                .requestId(request.getRequestId())
                .build();

        ByteBuf byteBuf = Unpooled.copiedBuffer(JSON.toJSONBytes(response));
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
