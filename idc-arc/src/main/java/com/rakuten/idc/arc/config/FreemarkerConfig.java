package com.rakuten.idc.arc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class FreemarkerConfig {

    @Value("${spring.view.prefix}")
    private String viewPrefix;

    @Value("${spring.view.suffix}")
    private String viewSuffix;
    
    @Value("${spring.freemarker.templateLoaderPath}")
    private String templatePath;

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        System.out.println("templatePath : " + templatePath);
        configurer.setTemplateLoaderPath(templatePath);
        configurer.setDefaultEncoding("UTF-8");
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
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setExposeSpringMacroHelpers(true);
        viewResolver.setExposeRequestAttributes(false);
        viewResolver.setExposeSessionAttributes(false);
        return viewResolver;
    }

}