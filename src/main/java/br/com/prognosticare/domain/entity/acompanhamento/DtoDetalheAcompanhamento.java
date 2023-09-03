package br.com.prognosticare.domain.entity.acompanhamento;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record DtoDetalheAcompanhamento(
        UUID id,
        TipoAcompanhamento tipoAcompanhamento,
        String medicacao,
        char statusEvento,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a")
        LocalDateTime dataAcompanhamento,
        int intervaloHora,
        TipoTemporarioControlado tipoTemporarioControlado,
        String prescricaoMedica
) {
    public DtoDetalheAcompanhamento(AcompanhamentoEntity acompanhamentoEntity){
        this(acompanhamentoEntity.getId(), acompanhamentoEntity.getTipoAcompanhamento(),acompanhamentoEntity.getMedicacao(),acompanhamentoEntity.getStatusEvento(), acompanhamentoEntity.getDataAcompanhamento(),
                acompanhamentoEntity.getIntervaloHora(), acompanhamentoEntity.getTipoTemporarioControlado(),acompanhamentoEntity.getPrescricaoMedica()
        );
    }
}
