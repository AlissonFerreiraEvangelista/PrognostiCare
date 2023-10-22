package br.com.prognosticare.domain.entity.acompanhamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.prognosticare.domain.enums.Status;
import br.com.prognosticare.domain.enums.TipoAcompanhamento;
import br.com.prognosticare.domain.enums.TipoTemporarioControlado;

import java.time.LocalDateTime;
import java.util.UUID;

public record DtoDetalheAcompanhamento(
    
        UUID id,

        TipoAcompanhamento tipoAcompanhamento,

        String medicacao,

        Status statusEvento,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a")
        LocalDateTime dataAcompanhamento,
        
        @JsonProperty(defaultValue = "0")
        Integer intervaloHora,

        Boolean notificacao,

        TipoTemporarioControlado tipoTemporarioControlado,

        String prescricaoMedica
) {
    public DtoDetalheAcompanhamento(AcompanhamentoEntity acompanhamentoEntity){
        this(acompanhamentoEntity.getId(),
         acompanhamentoEntity.getTipoAcompanhamento(),
         acompanhamentoEntity.getMedicacao(),
         acompanhamentoEntity.getStatusEvento(), 
         acompanhamentoEntity.getDataAcompanhamento(),
                acompanhamentoEntity.getIntervaloHora(), 
                acompanhamentoEntity.getNotificacao(),
                acompanhamentoEntity.getTipoTemporarioControlado(),acompanhamentoEntity.getPrescricaoMedica()
        );
    }
}
