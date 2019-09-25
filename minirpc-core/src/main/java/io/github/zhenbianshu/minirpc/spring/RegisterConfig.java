package io.github.zhenbianshu.minirpc.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zbs
 * @date 2019/9/13
 */
@Configuration
public class RegisterConfig {
    @Bean
    public RpcClientRegister getClientRegister() {
        return new RpcClientRegister();
    }

    @Bean
    public RpcProviderRegister getProviderRegister() {
        return new RpcProviderRegister();
    }
}
