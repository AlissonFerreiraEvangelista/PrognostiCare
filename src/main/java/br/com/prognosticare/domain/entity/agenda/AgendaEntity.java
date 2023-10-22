package br.com.prognosticare.domain.entity.agenda;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import br.com.prognosticare.domain.enums.Especialidade;
import br.com.prognosticare.domain.enums.Status;
import br.com.prognosticare.domain.enums.TipoExame;

import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.persistence.*;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime dataAgenda;

    private String local;

    private Integer intervaloData;

    @Column(columnDefinition= "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Status statusEvento;

    @Column(columnDefinition= "TEXT")
    private String observacao;

    @Enumerated(EnumType.STRING)
    private Especialidade especialista;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoExame tipoExame;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;

    private Boolean notificacao;
   
    public AgendaEntity(DtoCadastroAgenda dto){
        this.dataAgenda = dto.dataAgenda();
        this.descricao= dto.descricao();
        this.local= dto.local();
        this.statusEvento= Status.ABERTO;
        this.notificacao = false;
        this.intervaloData = dto.intervaloData();
        this.observacao= dto.observacao();
        this.especialista= dto.especialista();
        this.tipoExame= dto.tipoExame();

    }

     public void atualizaInformacao(DtoAtualizaAgenda dados) {
        Optional.ofNullable(dados.dataAgenda()).ifPresent(this::setDataAgenda);
        Optional.ofNullable(dados.descricao()).ifPresent(this::setDescricao);
        Optional.ofNullable(dados.local()).ifPresent(this::setLocal);
        Optional.ofNullable(dados.observacao()).ifPresent(this::setObservacao);
        Optional.ofNullable(dados.especialista()).ifPresent(this::setEspecialista);
        Optional.ofNullable(dados.tipoExame()).ifPresent(this::setTipoExame);
        Optional.ofNullable(dados.intervaloData()).ifPresent(this::setIntervaloData);
        Optional.ofNullable(dados.notificacao()).ifPresent(this::setNotificacao);

    }

    public void atualizaNotificacao(){
        this.notificacao = false;
    }

   
}
