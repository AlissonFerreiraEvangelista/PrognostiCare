package br.com.prognosticare.domain.entity.agenda;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DtoDetalheAgenda(

    UUID id,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    LocalDateTime dataAgenda,

    String local,

    char statusEvento,

    String descricao,

    String observacao,

    Especialidade especialista,

    TipoExame tipoExame    

) {
    
}
