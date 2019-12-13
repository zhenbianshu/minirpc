package io.github.zhenbianshu.minirpc.proxy;

import io.github.zhenbianshu.minirpc.core.Request;
import io.github.zhenbianshu.minirpc.core.Response;
import io.github.zhenbianshu.minirpc.core.ResponseFuture;
import io.github.zhenbianshu.minirpc.transport.Client;
import io.github.zhenbianshu.minirpc.transport.NettyClient;
import org.springframework.util.NumberUtils;

/**
 * @author zbs
 * @date 2019/11/12
 */
public class ServerNode extends Remote {

    private Client client;

    public ServerNode(String url) {
        String[] serverDetails = url.split(":");
        Integer port = NumberUtils.parseNumber(serverDetails[1], Integer.class);
        client = new NettyClient(serverDetails[0], port);
    }

    @Override
    public Response call(Request request) {
        ResponseFuture future = new ResponseFuture(request);

        Response response;
        try {
            client.send(request);
            response = (Response) future.get();
        } catch (Exception e) {
            ResponseFuture.remove(request);
            response = Response.builder().errorCode(10001).build();
        }
        return response;
    }

}
