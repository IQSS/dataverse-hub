package edu.harvard.iq.dataverse_hub.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Installation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long dvHubId;
    private String name;
    private String description;
    private String latitude;
    private String longitude;
    private String hostname;
    private String country;
    private String continent;
    private Date launchYear;
    private Boolean gdccMmember;
    private String doiAuthority;
    private String contact_email;

   

}
