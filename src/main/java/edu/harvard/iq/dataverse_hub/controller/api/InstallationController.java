package edu.harvard.iq.dataverse_hub.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.harvard.iq.dataverse_hub.beans.ServerMessageResponse;
import edu.harvard.iq.dataverse_hub.model.Installation;
import edu.harvard.iq.dataverse_hub.model.InstallationVersionInfo;
import edu.harvard.iq.dataverse_hub.service.InstallationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/installation")
public class InstallationController {

    @Autowired
    private InstallationService installationService;

    @GetMapping()
    @Tag(name = "Installation list", 
         description = "Rregistered dataverse installations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                        description = "Installation list success",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Installation.class),
                        examples = @ExampleObject("""
                            [{
                                "dvHubId": "DVN_HARVARD_DATAVERSE_2008",
                                "name": "Harvard Dataverse",
                                "description": "Share, archive, and get credit for your data. Find and cite data across all research fields.",
                                "latitude": 42.375646,
                                "longitude": "-71.113212",
                                "hostname": "dataverse.harvard.edu",
                                "country": "USA",
                                "continent": "North America",
                                "launchYear": 2008,
                                "gdccMember": true,
                                "doiAuthority": "10.791",
                                "contactEmail": "support@dataverse.harvard.edu"
                            }]
                            """))),
        @ApiResponse(responseCode = "400", 
                        description = "Bad Request on installation list",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject("""
                            {
                                "timestamp": "2024-11-01T19:39:15.953+00:00",
                                "code": 400,
                                "status": "Not Found",
                                "message": "No static resource api/unknown.",
                                "stackTrace": "org.springframework.web.servlet.resource.ResourceHttpRequestHandler.handleRequest(ResourceHttpRequestHandler.java:585)"
                            }
                            """))),
        @ApiResponse(responseCode = "500", 
                        description = "Internal Server Error on installation list",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject("""
                            {
                                "timestamp": "2024-11-01T19:42:40.470+00:00",
                                "code": 500,
                                "status": "Internal Server Error",
                                "message": "Not implemented",
                                "stackTrace": "edu.harvard.iq.dataverse_hub.controller.api.InstallationController.getInstallationsAPIController(InstallationController.java:54)"
                            }
                            """)))
    })
    @Operation(summary = "Get all installations", 
               description = "Returns a list of all registered Dataverse installations")
    public List<Installation> getInstallationsAPIController(){
        return installationService.findAll();
    }
    
    @PutMapping
    @SecurityRequirement(name = "api_key")
    @Tag(name = "Create Installation", 
         description = "Installation to add")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                        description = "Installation creation success",
                        content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Installation.class),
                            examples = @ExampleObject("""
                                {
                                    "dvHubId": "DVN_HARVARD_DATAVERSE_2008",
                                    "name": "Harvard Dataverse",
                                    "description": "Share, archive, and get credit for your data. Find and cite data across all research fields.",
                                    "latitude": 42.375646,
                                    "longitude": -71.113212,
                                    "hostname": "dataverse.harvard.edu",
                                    "country": "USA",
                                    "continent": "North America",
                                    "launchYear": 2008,
                                    "gdccMember": true,
                                    "doiAuthority": "10.791",
                                    "contactEmail": "support@dataverse.harvard.edu"
                                }
                                """))),
        @ApiResponse(responseCode = "400", 
                        description = "Bad Request on installation creation",
                        content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServerMessageResponse.class),
                            examples = @ExampleObject("""
                                {
                                    "timestamp": "2024-11-01T19:39:15.953+00:00",
                                    "code": 400,
                                    "status": "Not Found",
                                    "message": "No static resource api/unknown.",
                                    "stackTrace": "org.springframework.web.servlet.resource.ResourceHttpRequestHandler.handleRequest(ResourceHttpRequestHandler.java:585)"
                                }
                                """))),
        @ApiResponse(responseCode = "500", 
                        description = "Internal Server Error on installation creation",
                        content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServerMessageResponse.class),
                            examples = @ExampleObject("""
                                {
                                    "timestamp": "2024-11-01T19:42:40.470+00:00",
                                    "code": 500,
                                    "status": "Internal Server Error",
                                    "message": "Not implemented",
                                    "stackTrace": "edu.harvard.iq.dataverse_hub.controller.api.InstallationController.getInstallationsAPIController(InstallationController.java:54)"
                                }
                                """)))
    })
    @Operation(summary = "Create Installation", 
               description = "Create a new Dataverse installation")
    public Installation createInstallation(
                @RequestBody(description="Dataverse installation to be created",
                        content = @Content(examples = @ExampleObject("""
                            {
                                "dvHubId": "DVN_HARVARD_DATAVERSE_2008",
                                "name": "Harvard Dataverse",
                                "description": "Share, archive, and get credit for your data. Find and cite data across all research fields.",
                                "latitude": 42.375646,
                                "longitude": -71.113212,
                                "hostname": "dataverse.harvard.edu",
                                "country": "USA",
                                "continent": "North America",
                                "launchYear": 2008,
                                "gdccMember": true,
                                "doiAuthority": "10.791",
                                "contactEmail": "support@dataverse.harvard.edu"
                            }  
                    
                """))) Installation installation){
        return installationService.save(installation);
    }

    @GetMapping("status")
    @Tag(name = "Installation status", 
         description = "Dataverse installations status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                        description = "Installations Status list success",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = InstallationVersionInfo[].class),
                        examples = @ExampleObject("""
                                [{
                                    "dvHubId": "DVN_HARVARD_DATAVERSE_2008",
                                    "name": "Harvard Dataverse",
                                    "description": "Share, archive, and get credit for your data. Find and cite data across all research fields.",
                                    "latitude": 42.375646,
                                    "longitude": -71.113212,
                                    "hostname": "dataverse.harvard.edu",
                                    "country": "USA",
                                    "continent": "North America",
                                    "launchYear": 2008,
                                    "gdccMember": true,
                                    "doiAuthority": "10.791",
                                    "contactEmail": "support@dataverse.harvard.edu"
                                }]
                                """))),
        @ApiResponse(responseCode = "400", 
                        description = "Bad Request on installation status",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject("""
                                {
                                    "timestamp": "2024-11-01T19:39:15.953+00:00",
                                    "code": 400,
                                    "status": "Not Found",
                                    "message": "No static resource api/unknown.",
                                    "stackTrace": "org.springframework.web.servlet.resource.ResourceHttpRequestHandler.handleRequest(ResourceHttpRequestHandler.java:585)"
                                }
                                """))),
        @ApiResponse(responseCode = "500", 
                        description = "Internal Server Error on installation status",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ServerMessageResponse.class),
                        examples = @ExampleObject("""
                                {
                                    "timestamp": "2024-11-01T19:42:40.470+00:00",
                                    "code": 500,
                                    "status": "Internal Server Error",
                                    "message": "Not implemented",
                                    "stackTrace": "edu.harvard.iq.dataverse_hub.controller.api.InstallationController.getInstallationsAPIController(InstallationController.java:54)"
                                }
                                """)))
    })
    @Operation(summary = "Get installations status", 
               description = "Returns a list of the most recent status of all registered Dataverse installations")
    public List<InstallationVersionInfo> geInstallationsStatus(){
        return installationService.getInstallationInfo();
    }


}
