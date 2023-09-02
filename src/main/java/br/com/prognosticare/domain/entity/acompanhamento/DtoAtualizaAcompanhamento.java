package br.com.prognosticare.domain.entity.acompanhamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record DtoAtualizaAcompanhamento (
        @NotNull
        UUID id,
        @NotNull
        TipoAcompanhamento tipoAcompanhamento,
        String medicacao,
        @NotNull
        char statusEvento,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a")
        LocalDateTime dataAcompanhamento,
        int intervaloHora,
        TipoTemporarioControlado tipoTemporarioControlado,
        String prescricaoMedica
){
}
