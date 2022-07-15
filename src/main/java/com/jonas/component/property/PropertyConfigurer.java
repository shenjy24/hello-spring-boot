package com.jonas.component.property;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;

/**
 * @author shenjy
 * @createTime 2022/2/28 14:43
 * @description 获取配置属性工具
 */
@Component
public class PropertyConfigurer extends PropertySourcesPlaceholderConfigurer  {

    private static PropertyResolver propertyResolver;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     ConfigurablePropertyResolver propertyResolver) throws BeansException {
        super.processProperties(beanFactoryToProcess, propertyResolver);
        PropertyConfigurer.propertyResolver = propertyResolver;
    }

    public static String getString(String key) {
        return propertyResolver.getProperty(key);
    }

}
