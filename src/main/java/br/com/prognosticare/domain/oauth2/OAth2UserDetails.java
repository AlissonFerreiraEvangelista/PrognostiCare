package br.com.prognosticare.domain.oauth2;

import java.util.Map;

public abstract class OAth2UserDetails {

    protected Map<String, Object> attributes;

    public OAth2UserDetails(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getName();

    public abstract String getEmail();

    

    
}
