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
                "captureDate": "2024-11-02T02:05:31.711+00:00"
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

}
