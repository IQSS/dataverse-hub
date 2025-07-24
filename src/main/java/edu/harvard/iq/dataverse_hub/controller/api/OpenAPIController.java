package edu.harvard.iq.dataverse_hub.controller.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
    info = @Info(
        title = "Dataverse Hub API", 
        version = "1.0.1", 
        description = "API for Dataverse Hub",
        license = @License (name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0"),
        contact = @Contact(name = "Dataverse Hub", email = "support@dataverse.harvard.edu", url = "https://dataverse.org")        
    ),
    security = @SecurityRequirement(name = "api_key"),
    tags = {
        @Tag(name = "installations", description = "Installation status and information"),
        @Tag(name = "installation-metrics", description = "Metrics related to Dataverse Installations."),
        @Tag(name = "development-metrics", description = "Metrics related to development and code repositories.")
    }
)
@SecuritySchemes({   
    @SecurityScheme(
        name = "api_key",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        description = "Key for accessing the Dataverse Hub API")

})
public class OpenAPIController {}
