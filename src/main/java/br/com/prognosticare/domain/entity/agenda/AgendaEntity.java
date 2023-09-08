package br.com.prognosticare.domain.entity.agenda;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;

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

    private char statusEvento;

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
   
    public AgendaEntity(DtoCadastroAgenda dto){
        this.dataAgenda = dto.dataAgenda();
        this.descricao= dto.descricao();
        this.local= dto.local();
        this.statusEvento= 'A';
        this.intervaloData = dto.intervaloData();
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
        Optional.ofNullable(dados.intervaloData()).ifPresent(this::setIntervaloData);

    }

    public void atualizaAgenda(){
        this.dataAgenda = dataAgenda.plusDays(intervaloData);
    }

   
}
