package br.com.prognosticare.domain.entity.agenda;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prognosticare.domain.enums.Especialidade;
import br.com.prognosticare.domain.enums.TipoExame;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCadastroAgenda(
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a")
    LocalDateTime dataAgenda,

    @NotBlank
    String local,

    String descricao,

    String observacao,

    Integer intervaloData,

    Boolean notificacao,

    Especialidade especialista,

    @NotNull
    TipoExame tipoExame

) {
    
}
