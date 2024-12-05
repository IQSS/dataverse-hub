package edu.harvard.iq.dataverse_hub.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Schema(description = "Development metrics of releases from repositories")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DevMetricsReleases {
    
    @Id
    @Schema(description = "Tag name of the release",
            example = "v6.4.0")
    @JsonProperty("tag_name")
    private String tagName;

    @Schema(description = "Date when the release was published",
            example = "2024-10-31T20:13:03.422+00:00")
    @JsonProperty("published_at")
    private Date publishedAt;

    @Schema(description = "Name of the repository",
            example = "Dataverse")
    private String repoName;

    public String getTagName() {
        return tagName;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }


    @Override
    public String toString() {
        return "ReleasesMetrics{" +
                "tagName='" + getTagName() + '\'' +
                ", publishedAt=" + getPublishedAt() +
                ", repoName='" + getRepoName() + '\'' +
                '}';
    }
}
