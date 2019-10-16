package io.github.zhenbianshu.minirpc.spring;

import io.github.zhenbianshu.minirpc.config.ProviderConfig;
import io.github.zhenbianshu.minirpc.config.RefererConfig;
import io.github.zhenbianshu.minirpc.config.RegistryConfig;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author zbs
 * @date 2019/9/30
 */
public class MinirpcNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("provider", new MinirpcBeanDefinitionParser(ProviderConfig.class, true));
        registerBeanDefinitionParser("referer", new MinirpcBeanDefinitionParser(RefererConfig.class, true));
        registerBeanDefinitionParser("registry", new MinirpcBeanDefinitionParser(RegistryConfig.class, false));
    }

}
