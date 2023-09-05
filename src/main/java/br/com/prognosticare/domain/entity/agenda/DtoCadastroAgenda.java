package br.com.prognosticare.domain.entity.agenda;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record DtoCadastroAgenda(
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    LocalDateTime dataAgenda,

    String local,

    String descricao,

    String observacao,

    Especialidade especialista,

    TipoExame tipoExame

) {
    
}
