package management.infrastructure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Finance Context API")
                        .version("1.0")
                        .description("API para gestão de contextos financeiros com rastreamento de Correlation ID"));
    }

    @Bean
    public OperationCustomizer addGlobalHeader() {
        return (operation, handlerMethod) -> {
      
            Parameter correlationIdHeader = new Parameter()
                    .in("header")
                    .name("correlation-id")
                    .description("ID de correlação para logs")
                    .required(false);

            operation.addParametersItem(correlationIdHeader);
            return operation;
        };
    }
}


