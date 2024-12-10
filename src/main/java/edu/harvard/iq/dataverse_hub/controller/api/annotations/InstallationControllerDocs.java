package edu.harvard.iq.dataverse_hub.controller.api.annotations;

import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationMetrics;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.RequestParam;

import edu.harvard.iq.dataverse_hub.beans.APIPayloadSamples;
import edu.harvard.iq.dataverse_hub.beans.ServerMessageResponse;
import edu.harvard.iq.dataverse_hub.controller.api.response.InstallationsByCountry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


public @interface InstallationControllerDocs {
    
    @Target({ElementType.METHOD})    
    @Retention(RetentionPolicy.RUNTIME)
    @Tag(name = "dv-metrics", description = "Dataverse metrics operations")
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
    @Tag(name = "dv-metrics")
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
    @Tag(name = "dv-metrics")
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

    @Target({ElementType.METHOD})    
    @Retention(RetentionPolicy.RUNTIME)
    @Tag(name = "dv-metrics")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                        description = "Installation by country count success",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = InstallationsByCountry[].class),
                        examples = @ExampleObject(APIPayloadSamples.INSTALLATIONS_BY_COUNTRY))),
        @ApiResponse(responseCode = "400", 
                        description = "Bad Request on Installation by country count list",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_400))),
        @ApiResponse(responseCode = "500", 
                        description = "Internal Server Error on Installation by country count",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_500)))
    })
    @Operation(summary = "Get a count of the installations by country", 
                description = "Returns a count of the number of registered Dataverse installations by country")
    public @interface getInstallationsByCountry {}

    @Target({ElementType.METHOD})    
    @Retention(RetentionPolicy.RUNTIME)
    @Tag(name = "dv-metrics")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                        description = "Registered installations metrics success",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = InstallationMetrics[].class),
                        examples = @ExampleObject(APIPayloadSamples.INSTALLATION_ARRAY))),
        @ApiResponse(responseCode = "400", 
                        description = "Bad Request on registered installations metrics list",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_400))),
        @ApiResponse(responseCode = "500", 
                        description = "Internal Server Error on registered installations metrics",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_500)))
    })
    @Operation(summary = "Get the metrics of the registered installations", 
                description = "Returns a set of metrics for each one of the registered dataverse installations")
    public @interface getInstallationsMetrics {}

    @Target({ElementType.METHOD})    
    @Retention(RetentionPolicy.RUNTIME)
    @Tag(name = "dv-metrics")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                        description = "Registered installations metrics by month success",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = InstallationMetrics[].class),
                        examples = @ExampleObject(APIPayloadSamples.INSTALLATION_ARRAY))),
        @ApiResponse(responseCode = "400", 
                        description = "Bad Request on registered installations metrics by month list",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_400))),
        @ApiResponse(responseCode = "500", 
                        description = "Internal Server Error on registered installations metrics by month",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_500)))
    })
    @Operation(summary = "Get the metrics by month of the registered installations", 
                description = "Returns a set of monthly metrics for each one of the registered dataverse installations")
    public @interface getMonthlyInstallationsMetrics {}
        

}
