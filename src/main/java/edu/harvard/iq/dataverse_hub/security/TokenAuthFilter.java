package edu.harvard.iq.dataverse_hub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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


@Component
public class TokenAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

	
    @Override
	protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, 
                                    @SuppressWarnings("null") HttpServletResponse response, 
                                    @SuppressWarnings("null") FilterChain filterChain) throws ServletException, IOException {
		
        User user = userService.validateRequest(request);
        Authentication authenticationToken = new AnonymousAuthenticationToken(user.getUsername(), user, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
	}


    
}
