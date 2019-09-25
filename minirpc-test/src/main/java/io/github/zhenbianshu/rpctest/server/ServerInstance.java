package io.github.zhenbianshu.rpctest.server;

import io.github.zhenbianshu.rpctest.api.HelloService;
import io.github.zhenbianshu.minirpc.core.Provider;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author zbs
 * @date 2019/9/3
 */
@Slf4j
public class ServerInstance {
    public static void main(String[] args) {
        log.info("start");
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
                .className(HelloService.class.getName())
                .method(method)
                .build();

        Provider.PROVIDERS_MAP.put(HelloService.class.getName(), service);
    }
}
