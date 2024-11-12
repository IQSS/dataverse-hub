package edu.harvard.iq.dataverse_hub.controller.api.payloadBeans;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A representation of the number of Dataverse installations by country")
public class InstallationsByCountry {

    @Schema(description = "Country of the Dataverse installation",
            example = "United States")
    private String country;
    @Schema(description = "Number of Dataverse installations in the country",
            example = "3")
    private Long count;
    @Schema(description = "Date when the information was captured",
            example = "2024-10-31T20:13:03.422+00:00")
    private Date captureDate;

    public InstallationsByCountry(String country, Long count) {
        this.country = country;
        this.count = count;
        this.captureDate = new Date();
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Date getCaptureDate() {
        return this.captureDate;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }



}
