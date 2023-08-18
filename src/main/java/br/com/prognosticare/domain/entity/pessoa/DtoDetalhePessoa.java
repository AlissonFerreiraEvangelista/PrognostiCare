package br.com.prognosticare.domain.entity.pessoa;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import br.com.prognosticare.domain.entity.usuario.Usuario;

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
        Usuario usuario
) {
    public DtoDetalhePessoa(PessoaEntity pessoa) {
        this(pessoa.getPessoa_id(), pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento(),
                pessoa.getTipoSanguineo(), pessoa.getAlergia(), pessoa.getTipoResponsavel(),
                pessoa.getCartaoNacional(), pessoa.getCartaoPlanoSaude(), pessoa.getUsuario());
    }

    public DtoDetalhePessoa(Optional<PessoaEntity> pessoa) {
        this(pessoa.orElse(null)); 
    }
}
