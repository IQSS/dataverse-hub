package edu.harvard.iq.dataverse_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.harvard.iq.dataverse_hub.model.AccessToken;

public interface AccessTokenRepo extends JpaRepository<AccessToken, String> {
    
    @Query("SELECT a FROM AccessToken a WHERE a.tokenId = ?1")
    AccessToken findByTokenId(String tokenId);

}
