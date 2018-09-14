package de.uni.koeln.demmer.dennis.controller.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/uploadzip");
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/uploadtxt");
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/correct");
        registry.addInterceptor(new FileInterceptor()).addPathPatterns("/ner");
    }
}
