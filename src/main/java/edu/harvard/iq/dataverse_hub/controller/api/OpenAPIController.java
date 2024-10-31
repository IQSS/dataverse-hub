package edu.harvard.iq.dataverse_hub.controller.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@OpenAPIDefinition(
    info = @Info(
        title = "Dataverse Hub API", 
        version = "1.0", 
        description = "API for Dataverse Hub",
        license = @License (name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0"),
        contact = @Contact(name = "Dataverse Hub", email = "support@dataverse.harvard.edu", url = "https://dataverse.org")
    )
)
@SecuritySchemes({   
    @SecurityScheme(
        name = "api_key",
        type = SecuritySchemeType.APIKEY,
        scheme = "api_key",
        description = "API key for accessing the API")

})
public class OpenAPIController {}
