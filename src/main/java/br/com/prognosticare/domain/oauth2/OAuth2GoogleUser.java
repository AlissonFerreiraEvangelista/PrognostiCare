package br.com.prognosticare.domain.oauth2;

import java.util.Map;

public class OAuth2GoogleUser extends OAth2UserDetails{

    public OAuth2GoogleUser(Map<String, Object> attributes) {
        super(attributes);
        
    }

    @Override
    public String getName() {
       return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
       return (String) attributes.get("email");
    }
    
}
