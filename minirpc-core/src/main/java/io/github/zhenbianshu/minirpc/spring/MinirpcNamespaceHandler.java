package io.github.zhenbianshu.minirpc.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zbs
 * @date 2019/9/30
 */
public class MinirpcNamespaceHandler extends NamespaceHandlerSupport {
    public static final Set<String> REGISTRY_NAMES = new HashSet<>();

    @Override
    public void init() {
        registerBeanDefinitionParser("provider", new MinirpcBeanDefinitionParser(ProviderConfigBean.class, true));
        registerBeanDefinitionParser("referer", new MinirpcBeanDefinitionParser(RefererConfigBean.class, true));
        registerBeanDefinitionParser("registry", new MinirpcBeanDefinitionParser(RegistryConfigBean.class, false));
    }

}
