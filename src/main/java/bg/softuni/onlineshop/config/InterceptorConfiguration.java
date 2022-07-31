package bg.softuni.onlineshop.config;

import bg.softuni.onlineshop.web.interceptor.ProductPageVisitationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ProductPageVisitationInterceptor()).addPathPatterns("/products");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

