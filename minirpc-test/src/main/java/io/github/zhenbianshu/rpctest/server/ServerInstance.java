package io.github.zhenbianshu.rpctest.server;

import io.github.zhenbianshu.rpctest.api.HelloServiceImpl;
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
        HelloServiceImpl helloService = new HelloServiceImpl();

        Method method = null;
        try {
            method = HelloServiceImpl.class.getDeclaredMethod("say", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Provider service = Provider.builder()
                .classObject(helloService)
                .className(HelloServiceImpl.class.getName())
                .method(method)
                .build();

        Provider.PROVIDERS_MAP.put(HelloServiceImpl.class.getName(), service);
    }
}
