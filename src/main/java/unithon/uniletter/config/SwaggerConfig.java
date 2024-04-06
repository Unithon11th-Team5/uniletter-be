package unithon.uniletter.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(getUniletterServerInfo())
                .components(
                        new Components().addSecuritySchemes(
                                "oauth2 token",
                                new SecurityScheme()
                                        .scheme("Bearer ")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .bearerFormat("jwt")
                                        .name("oauth2")
                        ));
    }

    private Info getUniletterServerInfo() {
        return new Info().title("Uniletter Server API")
                .description("Uniletter Server API 명세서입니다.")
                .version("1.0.0");
    }
}
