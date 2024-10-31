package edu.harvard.iq.dataverse_hub.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        @ApiResponse(responseCode = "200", description = "Installation list success",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Installation.class)))),
        @ApiResponse(responseCode = "400", description = "Installation list service not available")
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
        @ApiResponse(responseCode = "200", description = "Installation created successfully"),
        @ApiResponse(responseCode = "403", description = "Not authorized to create installation")
    })
    @Operation(summary = "Create Installation", 
               description = "Create a new Dataverse installation")
    public Installation createInstallation(@RequestBody(description="Dataverse installation to be created") Installation installation){
        return installationService.save(installation);
    }

    @GetMapping("status")
    @Tag(name = "Installation status", 
         description = "Dataverse installations status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                description = "Most recent installation status success",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = InstallationVersionInfo.class)))),                                      
        @ApiResponse(responseCode = "400", description = "Installation status service not available")
    })
    @Operation(summary = "Get installations status", 
               description = "Returns a list of the most recent status of all registered Dataverse installations")
    public List<InstallationVersionInfo> geInstallationsStatus(){
        return installationService.getInstallationInfo();
    }


}
