package br.com.prognosticare.domain.entity.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SenhaTokenPublica {
    public final String email;
    public final Long createAtTimestamp;
    
}
