package br.com.prognosticare.domain.entity.pessoa;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import br.com.prognosticare.domain.enums.TipoSanguineo;

public record DtoDetalheDependente(
        UUID pessoaId,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String contato,
        TipoSanguineo tipoSanguineo,
        Boolean alergia,
        Boolean ativo,
        Boolean tipoResponsavel,
        String cartaoNacional,
        String cartaoPlanoSaude

) {
    public DtoDetalheDependente(PessoaEntity pessoa) {
        this(pessoa.getPessoaId(), pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento(),
        pessoa.getContato(),
        pessoa.getTipoSanguineo(), 
        pessoa.getAlergia(),
        pessoa.getAtivo(),
        pessoa.getTipoResponsavel(),
        pessoa.getCartaoNacional(),
         pessoa.getCartaoPlanoSaude());
    }

    public DtoDetalheDependente(Optional<PessoaEntity> pessoa) {
        this(pessoa.orElse(null)); 
    }
}
