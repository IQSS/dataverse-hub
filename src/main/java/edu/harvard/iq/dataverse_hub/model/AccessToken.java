package edu.harvard.iq.dataverse_hub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class AccessToken {

    @Id
    private String tokenId;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public String toString() {
        return "{" +
            " token_id='" + getTokenId() + "'" +
            ", user_id='" + getUserId() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessToken that = (AccessToken) o;
        return Objects.equals(getTokenId(), that.getTokenId()) && Objects.equals(getUserId(), that.getUserId());
    }

}
