package shop.mtcoding.blogv2._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration // 설정파일
public class WebMveConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry); // 기존에 하는 일은 둬야함
        
    registry.addResourceHandler("/images/**")
            .addResourceLocations("file:"+"./images/")
            .setCachePeriod(10) // 10 (초)
            .resourceChain(true)
            .addResolver(new PathResourceResolver());
    }
    
}
