package br.com.prognosticare.domain.entity.acompanhamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import org.checkerframework.checker.i18n.qual.LocalizableKey;

import java.time.LocalDateTime;
import java.util.UUID;

public record DtoCadastroAcompanhamento(
        @NotNull
        TipoAcompanhamento tipoAcompanhamento,
        String medicacao,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a")
        LocalDateTime dataAcompanhamento,
        int intervaloHora,
        @NotNull
        TipoTemporarioControlado tipoTemporarioControlado,
        String prescricaoMedica
) {
}
