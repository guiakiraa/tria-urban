package br.com.fiap.triaurban.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI configurarSwagger() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tria Urban - Global Solution 2025 - 1 Semestre - FIAP")
                        .description("Este projeto oferece uma implementação que possibilita "
                                + "a ajuda de pessoas que sofreram algum tipo de dano "
                                + "com desastres naturais.")
                        .summary("A fim de dimunuir os danos causados pelos desastres naturais, já que impedir é "
                                + "impossível(desastre NATURAL), desenvolvemos esse projeto com o intuíto de "
                                + "ajudar pessoas que sofreram com isso, possibilitando elas encontrarem "
                                + "pontos de distruição/abrigo com o suporte necessário.")
                        .version("v1.0.0")
                        .license(new License()
                                .url("www.fiap.com.br")
                                .name("Licença - Tria Urban - v1.0.0"))
                        .termsOfService("Termos de Serviço"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ));
    }
}
