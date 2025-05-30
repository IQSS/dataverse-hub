package edu.harvard.iq.dataverse_hub.controller.api.response;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A representation of the number of Dataverse installations by country")
public class InstallationsByCountry {

    @Schema(description = "Country that has at least 1 Dataverse installation",
            example = "United States")
    private String country;
    @Schema(description = "Number of Dataverse installations in the country",
            example = "3")
    private Long count;
    @Schema(description = "Date when the information was captured",
            example = "2024-10-31T20:13:03.422+00:00")
    private Date recordDate;

    public InstallationsByCountry() {
    }

    public InstallationsByCountry(String country, Long count) {
        this.country = country;
        this.count = count;
        this.recordDate = new Date();
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

    public Date getRecordDate() {
        return this.recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public String toString() {
        return "{" +
            " country='" + getCountry() + "'" +
            ", count='" + getCount() + "'" +
            ", recordDate='" + getRecordDate() + "'" +
            "}";
    }

}
