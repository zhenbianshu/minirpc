package io.github.zhenbianshu.minirpc.spring;

import io.github.zhenbianshu.minirpc.config.RegistryConfig;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author zbs
 * @date 2019/9/30
 */
public class RegistryConfigBean extends RegistryConfig implements BeanNameAware {
    @Override
    public void setBeanName(String name) {
        MinirpcNamespaceHandler.REGISTRY_NAMES.add(name);
    }
}
