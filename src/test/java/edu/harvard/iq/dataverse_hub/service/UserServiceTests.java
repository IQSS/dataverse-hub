package edu.harvard.iq.dataverse_hub.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;




@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {

        MockHttpServletRequest request = new MockHttpServletRequest();

        request.setMethod("GET");
        request.setRequestURI("/api/users");

        request.addHeader("api_key", "257d4485-173f-4a6d-913d-ee20c9d7bc06");
        userService.validateRequest(request);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.validateRequest(null);
        });
    }   


}
