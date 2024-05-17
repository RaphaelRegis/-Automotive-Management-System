package com.sistemaAutomotivo.SistemaAutomotivo.modulos.documentacao;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Raphael Regis",
            email = "raph4life17@gmail.com"
        ),
	    title = "Automotive-Management-System DOCUMENTATION",
        description = "Documentação OpenAPI para nossa aplicação",
	    version = "1.0"
    ),
    servers = {
        @Server(
            description = "Local ENV",
            url = "http://localhost:8080"
        )
    }
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT bearer authentication",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    
}
