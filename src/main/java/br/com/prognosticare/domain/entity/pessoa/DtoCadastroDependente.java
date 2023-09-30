package br.com.prognosticare.domain.entity.pessoa;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prognosticare.domain.enums.TipoSanguineo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DtoCadastroDependente(

        @NotBlank 
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}") String cpf,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate dataNascimento,

        TipoSanguineo tipoSanguineo,

        Boolean alergia,

        String tipoAlergia,

        String cartaoNacional,

        String cartaoPlanoSaude,
        
        Boolean doador
        ) {

}
