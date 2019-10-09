package io.github.zhenbianshu.minirpc.spring;

import io.github.zhenbianshu.minirpc.config.RefererConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author zbs
 * @date 2019/9/30
 */
public class RefererConfigBean<T> extends RefererConfig<T> implements FactoryBean<T> {
    @Override
    public T getObject() throws Exception {
        return getRef();
    }

    @Override
    public Class<?> getObjectType() {
        return getInterfaceName();
    }
}
