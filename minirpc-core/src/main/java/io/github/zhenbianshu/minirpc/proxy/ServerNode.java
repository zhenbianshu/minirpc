package io.github.zhenbianshu.minirpc.proxy;

import io.github.zhenbianshu.minirpc.core.Request;
import io.github.zhenbianshu.minirpc.core.Response;
import io.github.zhenbianshu.minirpc.transport.Client;
import org.springframework.util.NumberUtils;

import java.util.concurrent.CompletableFuture;

/**
 * @author zbs
 * @date 2019/11/12
 */
public class ServerNode extends Remote {

    private Client client;

    public ServerNode(String url) {
        String[] serverDetails = url.split(":");
        Integer port = NumberUtils.parseNumber(serverDetails[1], Integer.class);
        client = new Client(serverDetails[0], port);
        client.connect();
    }

    @Override
    public Response call(Request request) {
        CompletableFuture<Object> future = client.send(request);
        Response response;
        try {
            response = (Response) future.get();
            System.out.println(future.get());
        } catch (Exception e) {
            System.out.println("no response");
            response = Response.builder().errorCode(10001).build();
        }
        return response;
    }

}
