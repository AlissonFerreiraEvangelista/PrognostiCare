package br.com.prognosticare.domain.entity.agenda;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoDetalheAgenda(

    UUID id,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a")
    LocalDateTime dataAgenda,

    @NotBlank
    String local,

    char statusEvento,

    String descricao,

    Integer intervaloData,

    String observacao,

    Especialidade especialista,

    @NotNull
    TipoExame tipoExame    

) {

    public DtoDetalheAgenda(AgendaEntity agenda){
        this(
            agenda.getId(),
            agenda.getDataAgenda(),
            agenda.getLocal(),
            agenda.getStatusEvento(),
            agenda.getDescricao(),
            agenda.getIntervaloData(),
            agenda.getObservacao(),
            agenda.getEspecialista(),
            agenda.getTipoExame()
        );

    }
    
}
