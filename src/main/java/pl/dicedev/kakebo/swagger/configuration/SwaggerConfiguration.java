package pl.dicedev.kakebo.swagger.configuration;

import io.swagger.annotations.ApiParam;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docketApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.dicedev.kakebo"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(List.of(createSchema()))
                .securityContexts(List.of(createContext()));
    }

    private SecurityScheme createSchema() {
        return new ApiKey("kakeboApiKey", "Authorization", "header");
    }

    private SecurityContext createContext() {
        return SecurityContext.builder()
                .securityReferences(createReferences())
                .operationSelector(
                        it -> PathSelectors.any().test(it.requestMappingPattern())
                )
                .build();
    }

    private List<SecurityReference> createReferences() {
        var authorizationScope = new AuthorizationScope("global", "accessEverything");
        var authenticationScopes = new AuthorizationScope[1];
        authenticationScopes[0] = authorizationScope;

        return Collections.singletonList(new SecurityReference("kakeboApiKey", authenticationScopes));
    }

}
