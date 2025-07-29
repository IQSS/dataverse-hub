package edu.harvard.iq.dataverse_hub.controller.redirects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@Controller
public class RedirectsController {

    @RequestMapping("/")
    public void openApiRedirect(HttpServletResponse response) throws Exception {
        response.sendRedirect("/swagger-ui/index.html?url=/openapi");
    }

    @RequestMapping("/api/installation/**")
    public void installationRedirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String requestURI = request.getRequestURI();
        
        // Replace /api/installation with /api/installations
        String newPath = requestURI.replaceFirst("/api/installation", "/api/installations");
    
        // Forward to the new path only (RequestDispatcher handles query string automatically)
        RequestDispatcher dispatcher = request.getRequestDispatcher(newPath);
        dispatcher.forward(request, response);
    }

    
}
