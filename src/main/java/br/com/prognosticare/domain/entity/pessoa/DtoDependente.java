package br.com.prognosticare.domain.entity.pessoa;

import java.time.LocalDate;
import java.util.UUID;

import br.com.prognosticare.domain.enums.TipoSanguineo;

public record DtoDependente(
                UUID pessoaId,
                String nome,
                String cpf,
                LocalDate dataNascimento,
                TipoSanguineo tipoSanguineo,
                Boolean alergia,
                String tipoAlergia,
                Boolean tipoResponsavel,
                Boolean ativo,
                String cartaoNacional,
                String cartaoPlanoSaude) {

        public DtoDependente(PessoaEntity pessoa) {
                this(pessoa.getPessoaId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataNascimento(),
                pessoa.getTipoSanguineo(),
                pessoa.getAlergia(),
                pessoa.getTipoAlergia(),
                pessoa.getTipoResponsavel(),
                pessoa.getAtivo(),
                pessoa.getCartaoNacional(),
                pessoa.getCartaoPlanoSaude());
        }

}
