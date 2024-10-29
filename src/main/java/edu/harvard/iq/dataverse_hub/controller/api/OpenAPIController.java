package edu.harvard.iq.dataverse_hub.controller.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@OpenAPIDefinition(
    info = @Info(
        title = "Dataverse Hub API", 
        version = "1.0", 
        description = "API for Dataverse Hub"
    )
)
@SecuritySchemes({   
    @SecurityScheme(
        name = "api_key",
        type = SecuritySchemeType.APIKEY,
        scheme = "api_key" )

})
public class OpenAPIController {}
