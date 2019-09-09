package io.github.zhenbianshu.run;

import io.github.zhenbianshu.client.Connection;
import io.github.zhenbianshu.core.Request;

import java.util.concurrent.CompletableFuture;

/**
 * @author zbs
 * @date 2019/9/5
 */
public class Demo {
    public static void main(String[] args) {
        Connection connection = new Connection("127.0.0.1", 8122);
        connection.connect();
        Request request = Request.builder()
                .requestId(23544353453L)
                .parameters(new Object[]{"hello"})
                .serviceId(666)
                .build();
        CompletableFuture<Object> future = connection.send(request);
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            System.out.println("no response");
            e.printStackTrace();
        }
    }
}
