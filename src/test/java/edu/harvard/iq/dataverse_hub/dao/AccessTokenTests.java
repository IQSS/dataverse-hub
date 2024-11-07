package edu.harvard.iq.dataverse_hub.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import edu.harvard.iq.dataverse_hub.model.AccessToken;

public class AccessTokenTests {

    @Test
    public void testAccessToken() {
        assertDoesNotThrow(() -> {

            AccessToken token = new AccessToken();  

            token.setTokenId("1234");
            token.setUserId(1);

            AccessToken token2 = new AccessToken();
            token2.setTokenId("1234");
            token2.setUserId(1);


            assertEquals(token.getTokenId(), "1234");
            assertEquals(token.getUserId(), 1);

            assertNotNull(token.toString());
            assertEquals(token, token);
            assertEquals(token, token2);
        });
        

    }

}
