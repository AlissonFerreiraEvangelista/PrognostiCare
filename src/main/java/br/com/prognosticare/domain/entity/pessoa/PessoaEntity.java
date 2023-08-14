package br.com.prognosticare.domain.entity.pessoa;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.prognosticare.domain.entity.usuario.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
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
public class PessoaEntity {


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID pessoa_id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineo;

    private Boolean alergia;

    private Boolean tipoResponsavel;

    private String cartaoNacional;

    private String cartaoPlanoSaude;

    private Boolean ativo;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private List<PessoaEntity> dependente;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    private LocalDate dataAtualizacao;

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDate.now();
    }

    private LocalDate dataCriacao;

    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDate.now();
        dataAtualizacao = dataCriacao;
    }

   public void excluir() {
        this.ativo = false;
    }

    public void atualizarInformacoes(DtoAtualizaPessoa dados) {
        Optional.ofNullable(dados.nome()).ifPresent(this::setNome);
        Optional.ofNullable(dados.cpf()).ifPresent(this::setCpf);
        Optional.ofNullable(dados.dataNascimento()).ifPresent(this::setDataNascimento);
        Optional.ofNullable(dados.tipoSanguineo()).ifPresent(this::setTipoSanguineo);
        Optional.ofNullable(dados.alergia()).ifPresent(this::setAlergia);
        Optional.ofNullable(dados.tipoResponsavel()).ifPresent(this::setTipoResponsavel);
        Optional.ofNullable(dados.cartaoNacional()).ifPresent(this::setCartaoNacional);
        Optional.ofNullable(dados.cartaoPlanoSaude()).ifPresent(this::setCartaoPlanoSaude);
    }
    

    public PessoaEntity(DtoCadastroPessoa dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.dataNascimento = dto.dataNascimento();
        this.usuario = new Usuario(dto.usuario());
    }

    public PessoaEntity(String nome, String cpf, LocalDate dataNascimento, Usuario usuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.usuario = usuario;
    }

    
}