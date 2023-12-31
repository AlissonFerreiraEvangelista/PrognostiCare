package br.com.prognosticare.domain.entity.pessoa;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prognosticare.domain.enums.TipoSanguineo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DtoAtualizaDependente(
        
        @NotNull
        UUID pessoa_id,

        @NotBlank 
        String nome,

        @NotBlank @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}") String cpf,

        @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate dataNascimento,

        TipoSanguineo tipoSanguineo,

        Boolean alergia,

        String tipoAlergia,

        Boolean doador,

        Boolean ativo,

        String cartaoNacional,

        String cartaoPlanoSaude) {

}
