package io.github.zhenbianshu.minirpc.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author zbs
 * @date 2019/9/30
 */
public class MinirpcNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("provider", new MinirpcBeanDefinitionParser(ProviderConfigBean.class, true));
        registerBeanDefinitionParser("referer", new MinirpcBeanDefinitionParser(RefererConfigBean.class, true));
        registerBeanDefinitionParser("registry", new MinirpcBeanDefinitionParser(RefererConfigBean.class, false));
    }

}
