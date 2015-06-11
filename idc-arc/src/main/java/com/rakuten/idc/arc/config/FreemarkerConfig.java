package com.rakuten.idc.arc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.rakuten.idc.arc.constants.ArcConstants;

@Configuration
public class FreemarkerConfig {

    @Value(ArcConstants.VIEW_PREFIX)
    private String viewPrefix;

    @Value(ArcConstants.VIEW_SUFFIX)
    private String viewSuffix;

    @Value(ArcConstants.TEMPLATE_LOADER_PATH)
    private String templatePath;

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        System.out.println("templatePath : " + templatePath);
        configurer.setTemplateLoaderPath(templatePath);
        configurer.setDefaultEncoding(ArcConstants.DEFAULT_ENCODING);
        return configurer;
    }

    @Bean
    public ViewResolver getViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(true);
        System.out.println("Viewprefix : " + viewPrefix);
        System.out.println("viewSuffix : " + viewSuffix);
        viewResolver.setPrefix(viewPrefix);
        viewResolver.setSuffix(viewSuffix);
        viewResolver.setContentType(ArcConstants.DEFAULT_CONTENT_TYPE);
        viewResolver.setExposeSpringMacroHelpers(true);
        viewResolver.setExposeRequestAttributes(false);
        viewResolver.setExposeSessionAttributes(false);
        return viewResolver;
    }

}