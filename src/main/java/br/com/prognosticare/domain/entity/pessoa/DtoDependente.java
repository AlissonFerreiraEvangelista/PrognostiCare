package br.com.prognosticare.domain.entity.pessoa;

import java.time.LocalDate;
import java.util.UUID;

public record DtoDependente(
                UUID pessoa_id,
                String nome,
                String cpf,
                LocalDate dataNascimento,
                TipoSanguineo tipoSanguineo,
                Boolean alergia,
                String tipoAlergia,
                Boolean tipoResponsavel,
                String cartaoNacional,
                String cartaoPlanoSaude) {

        public DtoDependente(PessoaEntity pessoa) {
                this(pessoa.getPessoa_id(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataNascimento(),
                pessoa.getTipoSanguineo(),
                pessoa.getAlergia(),
                pessoa.getTipoAlergia(),
                pessoa.getTipoResponsavel(),
                pessoa.getCartaoNacional(),
                pessoa.getCartaoPlanoSaude());
        }

}