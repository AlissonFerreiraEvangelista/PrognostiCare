package br.com.prognosticare.domain.entity.agenda;

import java.time.LocalDateTime;
import java.util.UUID;


import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;


import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_agenda")
public class AgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime dataAgenda;

    private String local;

    @Column(columnDefinition= "TEXT")
    private String descricao;

    private char statusEvento;

    @Column(columnDefinition= "TEXT")
    private String observacao;

    private Especialidade especialista;
    
    private TipoExame tipoExame;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PessoaEntity pessoa;
   
    public AgendaEntity(DtoCadastroAgenda dto){
        this.dataAgenda = dto.dataAgenda();
        this.descricao= dto.descricao();
        this.local= dto.local();
        this.statusEvento= 'A';
        this.observacao= dto.observacao();
        this.especialista= dto.especialista();
        this.tipoExame= dto.tipoExame();

    }

     public void atualizaInformacao(DtoDetalheAgenda dados) {
        Optional.ofNullable(dados.dataAgenda()).ifPresent(this::setDataAgenda);
        Optional.ofNullable(dados.descricao()).ifPresent(this::setDescricao);
        Optional.ofNullable(dados.local()).ifPresent(this::setLocal);
        Optional.ofNullable(dados.statusEvento()).ifPresent(this::setStatusEvento);
        Optional.ofNullable(dados.observacao()).ifPresent(this::setObservacao);
        Optional.ofNullable(dados.especialista()).ifPresent(this::setEspecialista);
        Optional.ofNullable(dados.tipoExame()).ifPresent(this::setTipoExame);

    }

   
}
