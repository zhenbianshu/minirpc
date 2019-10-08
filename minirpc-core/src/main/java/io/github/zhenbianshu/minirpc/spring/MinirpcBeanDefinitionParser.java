package io.github.zhenbianshu.minirpc.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import java.lang.reflect.Field;

/**
 * @author zbs
 * @date 2019/9/30
 */
public class MinirpcBeanDefinitionParser implements BeanDefinitionParser {

    private final Class<?> beanClass;

    private final boolean required;

    public MinirpcBeanDefinitionParser(Class<?> beanClass, boolean required) {
        this.beanClass = beanClass;
        this.required = required;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String id = element.getAttribute("id");
        if (StringUtils.isEmpty(id) && required) {
            id = beanClass.getName();
        }

        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        if (parserContext.getRegistry().containsBeanDefinition(id)) {
            throw new IllegalStateException("Duplicate spring bean id " + id);
        }
        if (!StringUtils.isEmpty(id)) {
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        }

        for (Field field : beanClass.getDeclaredFields()) {
            String property = field.getName();
            if ("id".equals(property)) {
                beanDefinition.getPropertyValues().addPropertyValue(property, id);
                continue;
            }
            String value = element.getAttribute(property);
            if (StringUtils.isEmpty(value)) {
                continue;
            }

            Object reference;
            if ("ref".equals(property)) {
                reference = new RuntimeBeanReference(value);
            } else {
                reference = new TypedStringValue(value);
            }

            beanDefinition.getPropertyValues().addPropertyValue(property, reference);
        }


        return beanDefinition;
    }
}
