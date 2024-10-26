package edu.harvard.iq.dataverse_hub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.harvard.iq.dataverse_hub.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


//@Component
public class TokenAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
		
        User user = null;
        String authHeader = request.getHeader("API_KEY");

        if(authHeader != null) {
            user = userService.validateToken(authHeader);
        }

        if(user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } else {
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        }

        
	}

    
}
