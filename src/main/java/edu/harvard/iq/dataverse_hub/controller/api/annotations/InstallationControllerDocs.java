package edu.harvard.iq.dataverse_hub.controller.api.annotations;

import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import edu.harvard.iq.dataverse_hub.beans.APIPayloadSamples;
import edu.harvard.iq.dataverse_hub.beans.ServerMessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


public @interface InstallationControllerDocs {
    
    @Target({ElementType.METHOD})    
    @Retention(RetentionPolicy.RUNTIME)
    @Tag(name = "Installation list", 
            description = "Rregistered dataverse installations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                        description = "Installation list success",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Installation.class),
                        examples = @ExampleObject(APIPayloadSamples.INSTALLATION_ARRAY))),
        @ApiResponse(responseCode = "400", 
                        description = "Bad Request on installation list",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_400))),
        @ApiResponse(responseCode = "500", 
                        description = "Internal Server Error on installation list",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_500)))
    })
    @Operation(summary = "Get all installations", 
                description = "Returns a list of all registered Dataverse installations")
    public @interface GetInstallations {}

    @Target({ElementType.METHOD})    
    @Retention(RetentionPolicy.RUNTIME)
    @SecurityRequirement(name = "api_key")
    @Tag(name = "Create Installation", 
            description = "Installation to add")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                        description = "Installation creation success",
                        content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Installation.class),
                            examples = @ExampleObject(APIPayloadSamples.INSTALLATION))),
        @ApiResponse(responseCode = "400", 
                        description = "Bad Request on installation creation",
                        content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServerMessageResponse.class),
                            examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_400))),
        @ApiResponse(responseCode = "500", 
                        description = "Internal Server Error on installation creation",
                        content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServerMessageResponse.class),
                            examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_500)))
    })
    @Operation(summary = "Create Installation", 
                description = "Create a new Dataverse installation")
    @RequestBody(description="Dataverse installation to be created",
                            content = @Content(examples = @ExampleObject(APIPayloadSamples.INSTALLATION)))            
    public @interface CreateInstallation {}

    @Target({ElementType.METHOD})    
    @Retention(RetentionPolicy.RUNTIME)
    @Tag(name = "Installation status", 
        description = "Dataverse installations status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                        description = "Installations Status list success",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = InstallationVersionInfo[].class),
                        examples = @ExampleObject(APIPayloadSamples.INSTALLATION_ARRAY))),
        @ApiResponse(responseCode = "400", 
                        description = "Bad Request on installation status",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_400))),
        @ApiResponse(responseCode = "500", 
                        description = "Internal Server Error on installation status",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_500)))
    })
    @Operation(summary = "Get installations status", 
            description = "Returns a list of the most recent status of all registered Dataverse installations")
    public @interface GetInstallationsStatus {}

}
