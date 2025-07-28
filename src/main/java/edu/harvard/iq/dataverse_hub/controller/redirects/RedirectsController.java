package edu.harvard.iq.dataverse_hub.controller.redirects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class RedirectsController {

    @RequestMapping("/")
    public void openApiRedirect(HttpServletResponse response) throws Exception {
        response.sendRedirect("/swagger-ui/index.html?url=/openapi");
    }
   
    
}
