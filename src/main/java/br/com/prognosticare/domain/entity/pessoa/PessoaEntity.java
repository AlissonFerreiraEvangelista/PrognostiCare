package br.com.prognosticare.domain.entity.pessoa;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prognosticare.domain.entity.acompanhamento.AcompanhamentoEntity;
import br.com.prognosticare.domain.entity.agenda.AgendaEntity;
import br.com.prognosticare.domain.enums.TipoSanguineo;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pessoa")
public class PessoaEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID pessoa_id;

    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineo;

    private String contato;

    private Boolean alergia;

    private String tipoAlergia;

    private Boolean tipoResponsavel;

    private String cartaoNacional;

    private String cartaoPlanoSaude;

    private Boolean ativo;

    private Boolean doador;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsavel_id")
    private PessoaEntity responsavel;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private List<PessoaEntity> dependente;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<AgendaEntity> agendas;

<<<<<<< HEAD
=======

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<AcompanhamentoEntity> acompanhamentos;


>>>>>>> 38ce6d544b29421319041c6ceb4c2bc0a7c206df
    public void excluir() {
        this.ativo = false;
    }

    public void atualizarInformacoes(DtoAtualizaPessoa dados) {
        Optional.ofNullable(dados.nome()).ifPresent(this::setNome);
        Optional.ofNullable(dados.cpf()).ifPresent(this::setCpf);
        Optional.ofNullable(dados.dataNascimento()).ifPresent(this::setDataNascimento);
        Optional.ofNullable(dados.tipoSanguineo()).ifPresent(this::setTipoSanguineo);
        Optional.ofNullable(dados.tipoAlergia()).ifPresent(this::setTipoAlergia);
        Optional.ofNullable(dados.contato()).ifPresent(this::setContato);
        Optional.ofNullable(dados.alergia()).ifPresent(this::setAlergia);
        Optional.ofNullable(dados.doador()).ifPresent(this::setDoador);
        Optional.ofNullable(dados.cartaoNacional()).ifPresent(this::setCartaoNacional);
        Optional.ofNullable(dados.cartaoPlanoSaude()).ifPresent(this::setCartaoPlanoSaude);
    }

    public PessoaEntity(DtoCadastroPessoa dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.dataNascimento = dto.dataNascimento();
        this.tipoResponsavel = true;
        this.email = dto.email();
        this.password = dto.password();
    }

    public PessoaEntity(String nome, String cpf, LocalDate dataNascimento, String email, String password) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public PessoaEntity(@Valid DtoCadastroDependente dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.dataNascimento = dto.dataNascimento();
        this.alergia = dto.alergia();
        this.tipoSanguineo = dto.tipoSanguineo();
        this.tipoResponsavel = false;
        this.cartaoNacional = dto.cartaoNacional();
        this.cartaoPlanoSaude = dto.cartaoNacional();
    }

}