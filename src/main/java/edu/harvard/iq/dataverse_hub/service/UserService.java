package edu.harvard.iq.dataverse_hub.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Service
public class UserService {

    public User validateToken(String token) {

        User user = null;
        
        if(token != null){
            ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new GrantedAuthority() {
                @Override
                    public String getAuthority() {
                        return "DVH_ADMIN";
                    }
            });
            user =  new User("user-name", "user-password", authorities);
        }
        return user;
    }

}
