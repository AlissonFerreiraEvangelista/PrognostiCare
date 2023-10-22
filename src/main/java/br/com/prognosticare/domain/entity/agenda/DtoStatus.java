package br.com.prognosticare.domain.entity.agenda;

import br.com.prognosticare.domain.enums.Status;
import jakarta.validation.constraints.NotNull;

public record DtoStatus(
    @NotNull
    Status statusEvento
) {
    
}
