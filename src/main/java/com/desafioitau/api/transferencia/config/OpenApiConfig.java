package com.desafioitau.api.transferencia.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
class OpenApiConfig {
    private String apiVersion = "1.0.0";
    private String urlLocalServer = "http://localhost:8080/";
    private String internalServerErrorResponse = "InternalServerErrorResponse";
    private String notfoundResponse = "NotFoundResponse";
    private String badRequestResponse = "BadRequestResponse";

    @Bean
    public OpenAPI api() {
        Server localServer = new Server().description("Local").url(this.urlLocalServer);
        return new OpenAPI()
                .servers(Arrays.asList(localServer))
                .info(new Info()
                        .title("Desafio Itaú")
                        .description("Essa API é responsável por efetuar transferências bancárias.")
                        .version(this.apiVersion))
                .components(new Components()
                        .responses(generateDefaultResponses()));
    }

    private Map<String, ApiResponse> generateDefaultResponses() {
        Map<String, ApiResponse> responsesMap = new HashMap<>();
        Content content = new Content();
        content.addMediaType(
                "application/json",
                new MediaType()
        );
        responsesMap.put(internalServerErrorResponse, new ApiResponse().description("Internal Server Error").content(content));
        responsesMap.put(notfoundResponse, new ApiResponse().description("Not Found").content(content));
        responsesMap.put(badRequestResponse, new ApiResponse().description("Bad Request").content(content));
        return responsesMap;
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return new OpenApiCustomizer() {
            @Override
            public void customise(OpenAPI openApi) {
                openApi.getPaths().values().forEach(item -> {
                    item.readOperationsMap().forEach((httpMethod, operation) -> {
                        switch (httpMethod) {
                            case GET:
                                Map<String, ApiResponse> responses = operation.getResponses();
                                responses.put("500", new ApiResponse().$ref(internalServerErrorResponse));
                                responses.put("404", new ApiResponse().$ref(notfoundResponse));
                                responses.put("400", new ApiResponse().$ref(badRequestResponse));
                                break;
                        }
                    });
                });
            }
        };
    }
}
