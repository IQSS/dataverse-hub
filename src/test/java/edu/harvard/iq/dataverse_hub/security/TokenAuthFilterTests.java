package edu.harvard.iq.dataverse_hub.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockFilterChain;


@SpringBootTest
public class TokenAuthFilterTests {

    @Autowired
    TokenAuthFilter tokenAuthFilter;

    @Test
    public void testTokenAuthFilter() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        assertDoesNotThrow(() -> {
            tokenAuthFilter.doFilterInternal(request, response, filterChain);
        });


        assertThrows(IllegalArgumentException.class, () -> {
            tokenAuthFilter.doFilterInternal(null, null, null);

        });
    
    }

}
