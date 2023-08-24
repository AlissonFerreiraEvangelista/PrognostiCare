package br.com.prognosticare.domain.entity.pessoa;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


public record DtoDetalhePessoa(
        UUID pessoa_id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        TipoSanguineo tipoSanguineo,
        Boolean alergia,
        Boolean tipoResponsavel,
        String cartaoNacional,
        String cartaoPlanoSaude,
        String email,
        String password
) {
    public DtoDetalhePessoa(PessoaEntity pessoa) {
        this(pessoa.getPessoa_id(), pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento(),
                pessoa.getTipoSanguineo(), pessoa.getAlergia(), pessoa.getTipoResponsavel(),
                pessoa.getCartaoNacional(), pessoa.getCartaoPlanoSaude(), pessoa.getEmail(), pessoa.getPassword());
    }

    public DtoDetalhePessoa(Optional<PessoaEntity> pessoa) {
        this(pessoa.orElse(null)); 
    }
}
