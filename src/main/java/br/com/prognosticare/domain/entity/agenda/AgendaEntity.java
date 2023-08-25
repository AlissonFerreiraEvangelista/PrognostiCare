package br.com.prognosticare.domain.entity.agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
   
    
}
