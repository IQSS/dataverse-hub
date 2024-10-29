package edu.harvard.iq.dataverse_hub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AccessToken {

    @Id
    private String token_id;
    private Integer user_id;

    public Integer getUserId() {
        return user_id;
    }

}
