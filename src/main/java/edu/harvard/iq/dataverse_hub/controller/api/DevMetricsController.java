package edu.harvard.iq.dataverse_hub.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.harvard.iq.dataverse_hub.controller.api.annotations.DevMetricsControllerDocs;
import edu.harvard.iq.dataverse_hub.model.DevMetrics;
import edu.harvard.iq.dataverse_hub.model.DevMetricsReleases;
import edu.harvard.iq.dataverse_hub.service.DevMetricsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/dev")
public class DevMetricsController {

    @Autowired
    private DevMetricsService devMetricsService;

    @GetMapping()
    @DevMetricsControllerDocs.GetDevMetrics
    public DevMetrics getDevMetrics() {
        return devMetricsService.getLatestDevMetrics();
    }

    @GetMapping("releases")
    @DevMetricsControllerDocs.GetDevMetricsReleases
    public List<DevMetricsReleases> getMethodName() {
        return devMetricsService.getAllDevMetricsReleases();
    }
    



}
