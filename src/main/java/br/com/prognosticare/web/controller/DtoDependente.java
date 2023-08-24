package br.com.prognosticare.web.controller;

import java.time.LocalDate;
import java.util.UUID;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import br.com.prognosticare.domain.entity.pessoa.TipoSanguineo;

public record DtoDependente(
        UUID pessoa_id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        TipoSanguineo tipoSanguineo,
        Boolean alergia,
        Boolean tipoResponsavel,
        String cartaoNacional,
        String cartaoPlanoSaude) {

     public DtoDependente(PessoaEntity pessoa) {
        this(pessoa.getPessoa_id(), pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento(),
                pessoa.getTipoSanguineo(), pessoa.getAlergia(), pessoa.getTipoResponsavel(),
                pessoa.getCartaoNacional(), pessoa.getCartaoPlanoSaude());
    }

}
