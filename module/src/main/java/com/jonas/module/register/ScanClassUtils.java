package com.jonas.module.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class ScanClassUtils implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<Class<?>> getClasses(String packagePrefix) {
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
        Resource[] resources;
        ArrayList<Class<?>> result = new ArrayList<>();
        try {
            String search = packagePrefix.replaceAll("\\.", "/");
            resources = resolver.getResources(String.format("classpath*:%s/**/*.class", search));

            for (Resource r : resources) {
                MetadataReader reader = metaReader.getMetadataReader(r);
                String className = reader.getClassMetadata().getClassName();
                result.add(Class.forName(className));
            }

            return result;
        } catch (Exception e) {
            log.error("扫描类错误", e);
            return Collections.emptyList();
        }
    }
}
