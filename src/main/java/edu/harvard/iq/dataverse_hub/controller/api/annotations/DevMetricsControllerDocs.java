package edu.harvard.iq.dataverse_hub.controller.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import edu.harvard.iq.dataverse_hub.beans.APIPayloadSamples;
import edu.harvard.iq.dataverse_hub.beans.ServerMessageResponse;
import edu.harvard.iq.dataverse_hub.model.Installation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

public @interface DevMetricsControllerDocs {
    
    @Target({ElementType.METHOD})    
    @Retention(RetentionPolicy.RUNTIME)
    @Tag(name = "dev-metrics", description = "Dev metrics operations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                        description = "Dev metrics success",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Installation.class),
                        examples = @ExampleObject(APIPayloadSamples.DEV_METRICS))),
        @ApiResponse(responseCode = "400", 
                        description = "Bad Request on dev-metrics",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_400))),
        @ApiResponse(responseCode = "500", 
                        description = "Internal Server Error on idev-metricst",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_500)))
    })
    @Operation(summary = "Get all metrics from tracked repositories", 
                description = "Returns the latest metrics from all tracked repositories")
    public @interface GetDevMetrics {}

    @Target({ElementType.METHOD})    
    @Retention(RetentionPolicy.RUNTIME)
    @Tag(name = "dev-metrics")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                        description = "Releases list success",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Installation.class),
                        examples = @ExampleObject(APIPayloadSamples.DEV_RELEASES))),
        @ApiResponse(responseCode = "400", 
                        description = "Bad Request on dev-releases list",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_400))),
        @ApiResponse(responseCode = "500", 
                        description = "Internal Server Error on dev-releases list",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject(APIPayloadSamples.SERVER_RESPONSE_500)))
    })
    @Operation(summary = "Get all releases information", 
                description = "Returns a list with the information from the releases")
    public @interface GetDevMetricsReleases {}

}
