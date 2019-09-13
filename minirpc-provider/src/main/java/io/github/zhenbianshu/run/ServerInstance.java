package io.github.zhenbianshu.run;

import io.github.zhenbianshu.core.Provider;
import io.github.zhenbianshu.transport.Server;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author zbs
 * @date 2019/9/3
 */
@Slf4j
public class ServerInstance {
    public static void main(String[] args) {
        initService();

        Server server = new Server();
        server.init();
    }

    private static void initService() {
        HelloService helloService = new HelloService();

        Method method = null;
        try {
            method = HelloService.class.getDeclaredMethod("say", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Provider service = Provider.builder()
                .classObject(helloService)
                .id(666)
                .method(method)
                .build();

        Provider.PROVIDERS_MAP.put(666, service);
    }
}
