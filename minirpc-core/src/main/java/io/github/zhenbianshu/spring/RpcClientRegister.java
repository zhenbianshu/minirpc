package io.github.zhenbianshu.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * 读取 {@link RpcClientConfig} 的配置，向 Spring 内注册 stub
 *
 * @author zbs
 * @date 2019/9/13
 */
public class RpcClientRegister implements ApplicationContextAware, BeanFactoryPostProcessor {
    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String, RpcClientConfig> rpcClientConfigs = applicationContext.getBeansOfType(RpcClientConfig.class);
        if (CollectionUtils.isEmpty(rpcClientConfigs)) {
            return;
        }

        rpcClientConfigs.entrySet().stream()
                .forEach((entry) -> {

                });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
