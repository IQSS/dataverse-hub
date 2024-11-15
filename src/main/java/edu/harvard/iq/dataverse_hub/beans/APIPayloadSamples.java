package edu.harvard.iq.dataverse_hub.beans;

public abstract class APIPayloadSamples {

    public static final String INSTALLATION = """
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
            """;

    public static final String INSTALLATION_ARRAY = "[" + INSTALLATION + "]";

    public static final String INSTALLATION_STATUS_ARRAY = """
            {
                "recordId": 1007,
                "installation": {
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
                },
                "status": "OK",
                "version": "6.4",
                "build": "1609-906f874",
                "recordDate": "2024-11-02T02:05:31.711+00:00"
            }
            """;

    public static final String SERVER_RESPONSE_400 = """
            {
                "timestamp": "2024-11-01T19:39:15.953+00:00",
                "code": 400,
                "status": "Not Found",
                "message": "No static resource api/unknown.",
                "stackTrace": "org.springframework.web.servlet.resource.ResourceHttpRequestHandler.handleRequest(ResourceHttpRequestHandler.java:585)"
            }
            """;

    public static final String SERVER_RESPONSE_500 = """
         {
            "timestamp": "2024-11-01T19:42:40.470+00:00",
            "code": 500,
            "status": "Internal Server Error",
            "message": "Not implemented",
            "stackTrace": "edu.harvard.iq.dataverse_hub.controller.api.InstallationController.getInstallationsAPIController(InstallationController.java:54)"
        }
        """;

    public static final String DEV_METRICS = """
        {
            "watchers": 882,
            "forks": 493,
            "open_issues": 810,
            "subscribers_count": 68,
            "name": "dataverse"
        }
        """;

    public static final String DEV_RELEASES = """
        [
            {
                "repoName": "dataverse",
                "tag_name": "v6.4",
                "published_at": "2024-09-30T16:30:02.000+00:00"
            }
        ]
        """;

    public static final String INSTALLATIONS_BY_COUNTRY = """
        [
            {
                "country": "USA",
                "count": 17,
                "recordDate": "2024-11-14T21:01:03.401+00:00"
            }
        ]
        """;

    public static final String INSTALLATION_METRICS = """
        [
            {   
                "installation": {
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
                },
                "recordDate": "2024-11-14T21:08:31.516+00:00",
                "files": 2155868,
                "downloads": 77682848,
                "datasets": 97838,
                "harvestedDatasets": 85639,
                "localDatasets": 97838,
                "dataverses": 6889
            }
        ]
            """;
        

}
