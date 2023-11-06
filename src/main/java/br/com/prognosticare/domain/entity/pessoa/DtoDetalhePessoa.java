package br.com.prognosticare.domain.entity.pessoa;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prognosticare.domain.enums.TipoSanguineo;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public record DtoDetalhePessoa(
        UUID pessoaId,
        String nome,
        String cpf,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate dataNascimento,
        String contato,
        TipoSanguineo tipoSanguineo,
        Boolean alergia,
        String tipoAlergia,
        Boolean tipoResponsavel,
        Boolean doador,
        String cartaoNacional,
        String cartaoPlanoSaude,
        String email,
        String password) {
    public DtoDetalhePessoa(PessoaEntity pessoa) {
        this(
                pessoa.getPessoaId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getDataNascimento(),
                pessoa.getContato(),
                pessoa.getTipoSanguineo(),
                pessoa.getAlergia(),
                pessoa.getTipoAlergia(),
                pessoa.getTipoResponsavel(),
                pessoa.getDoador(),
                pessoa.getCartaoNacional(),
                pessoa.getCartaoPlanoSaude(),
                pessoa.getEmail(),
                pessoa.getPassword()
                );
    }

    public DtoDetalhePessoa(Optional<PessoaEntity> pessoa) {
        this(pessoa.orElse(null));
    }
}
