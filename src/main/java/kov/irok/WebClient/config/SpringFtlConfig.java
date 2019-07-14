package kov.irok.WebClient.config;

import kov.irok.WebClient.service.UserService;
import kov.irok.WebClient.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@ComponentScan("kov.irok.*")
public class SpringFtlConfig {

    @Bean(name = "viewResolver")
    public ViewResolver getViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        System.out.println("Create Bean viewResolver");

        viewResolver.setCache(true);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html; charset=windows-1251");
        return viewResolver;
    }

    @Bean(name = "freemarkerConfig")
    public FreeMarkerConfigurer getFreemarkerConfig() {
        FreeMarkerConfigurer config = new FreeMarkerConfigurer();

        // Folder containing FreeMarker templates.
        config.setTemplateLoaderPath("/WEB-INF/views/");
        return config;
    }

}
