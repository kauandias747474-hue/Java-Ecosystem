package finance.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import poc.management.api.ContextInterceptor;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.finance.context", "poc.management"})
public class Main implements WebMvcConfigurer {

    private final ContextInterceptor contextInterceptor;

    public Main(ContextInterceptor contextInterceptor) {
        this.contextInterceptor = contextInterceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    
        registry.addInterceptor(contextInterceptor);
    }
}
