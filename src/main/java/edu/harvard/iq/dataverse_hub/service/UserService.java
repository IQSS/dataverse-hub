package edu.harvard.iq.dataverse_hub.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import edu.harvard.iq.dataverse_hub.model.AccessToken;
import edu.harvard.iq.dataverse_hub.repository.AccessTokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Service
public class UserService {

    @Autowired
    private AccessTokenRepo accessTokenRepo;

    public User validateRequest(HttpServletRequest request) {

        String apiToken = request.getHeader("api_key");
        AccessToken token = accessTokenRepo.findByTokenId(apiToken);
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
      
        if(token != null){
            
            authorities.add(new GrantedAuthority() {
                @Override
                    public String getAuthority() {
                        return "DVH_ADMIN";
                    }
            });
            User user = new User(token.getUserId() == null ? "" : token.getUserId().toString(), "", authorities);
            return user;
        } else {
            
            authorities.add(new GrantedAuthority() {
                @Override
                    public String getAuthority() {
                        return "PUBLIC";
                    }
            });

            User user = new User("GUEST", "", authorities);
            return user;
        }
    }

}
