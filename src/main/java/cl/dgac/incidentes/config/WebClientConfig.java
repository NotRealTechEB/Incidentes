package cl.dgac.incidentes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class WebClientConfig {

    @Bean
    public OpenAPI custonApi(){
        return new OpenAPI().info(new Info()
        .title("Api Incidentes")
        .version("1.5")
        .description("descripcion del sistema")
    );
    }
}
