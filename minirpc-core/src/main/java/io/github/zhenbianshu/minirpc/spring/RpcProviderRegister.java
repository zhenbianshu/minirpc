package io.github.zhenbianshu.minirpc.spring;

import io.github.zhenbianshu.minirpc.core.Provider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 读取 {@link RpcProviderConfig} 的配置
 * 向 {@link Provider#PROVIDERS_MAP} 内注册服务
 *
 * @author zbs
 * @date 2019/9/13
 */
public class RpcProviderRegister implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
