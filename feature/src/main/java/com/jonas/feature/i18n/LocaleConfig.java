package com.jonas.feature.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * https://blog.51cto.com/u_15288542/5685503
 */
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    /**
     * 注册 ReloadableResourceBundleMessageSource
     * i18n目录下的国际化文件不要手动创建，使用IDEA的resource bundle进行创建
     */
    @Bean("reloadableResourceBundleMessageSource")
    public MessageSource initReloadableResourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //指定读取国际化配置文件的basename
        messageSource.setBasename(ResourceUtils.CLASSPATH_URL_PREFIX + "i18n/messages");
        //指定编码
        messageSource.setDefaultEncoding("UTF-8");
        //指定缓存时间
        messageSource.setCacheSeconds(60);
        return messageSource;
    }
}
