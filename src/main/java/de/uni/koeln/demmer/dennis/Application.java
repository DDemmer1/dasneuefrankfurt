package de.uni.koeln.demmer.dennis;

//import de.uni.koeln.demmer.dennis.controller.storage.StorageProperties;
//import de.uni.koeln.demmer.dennis.controller.storage.StorageService;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})


// Boot Config Jetty Server

//public class Application extends SpringBootServletInitializer{
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return configureApplication(builder);
//    }
//
//    public static void main(String[] args) {
//        configureApplication(new SpringApplicationBuilder()).run(args);
//    }
//
//    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
//        return builder.sources(Application.class).bannerMode(Banner.Mode.OFF);
//    }


//    Development Boot
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }




    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/").allowedOrigins("http://localhost:63342");
            }
        };
    }

}








