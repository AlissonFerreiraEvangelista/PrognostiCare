package br.com.prognosticare.domain.entity.acompanhamento;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;
import java.util.UUID;

public record DtoCadastroAcompanhamento(
        @NotNull
        TipoAcompanhamento tipoAcompanhamento,

        String medicacao,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a")
        LocalDateTime dataAcompanhamento,

        Integer intervaloHora,

        @NotNull
        TipoTemporarioControlado tipoTemporarioControlado,

        String prescricaoMedica

) {
}
