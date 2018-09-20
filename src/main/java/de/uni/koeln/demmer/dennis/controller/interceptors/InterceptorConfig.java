package de.uni.koeln.demmer.dennis.controller.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Interceptor config Bean
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * Fügt der InterceptorRegistry den FileInterceptor für die verschiedenen Controller hinzu
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/zip/{param}");
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/txt/{param}");
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/correct");
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/ner");
    }
}
