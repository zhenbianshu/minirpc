package io.github.zhenbianshu.minirpc.spring;

import io.github.zhenbianshu.minirpc.config.ProviderConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author zbs
 * @date 2019/9/30
 */
public class ProviderConfigBean<T> extends ProviderConfig<T>
        implements
        FactoryBean<T> {

    @Override
    public T getObject() throws Exception {
        return getRef();
    }

    @Override
    public Class<?> getObjectType() {
        return getInterfaceName();
    }
}
