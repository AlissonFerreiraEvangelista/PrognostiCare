package br.com.prognosticare.domain.entity.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleName {
    //@JsonProperty("ADMIN")
    ROLE_ADMIN,
    //@JsonProperty("USER")
    ROLE_USER;

    @Getter
    private String roleName;
    

}
