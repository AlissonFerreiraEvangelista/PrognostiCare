package br.com.prognosticare.domain.entity.acompanhamento;


import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_acompanhamento")
public class AcompanhamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String medicacao;

    private char statusEvento;

    private LocalDateTime dataAcompanhamento;

    private int intervaloHora;

    private char tipoTemporarioControlado;

    @Column(columnDefinition = "TEXT")
    private String prescricaoMedica;

    
    public void atualizaProxaMedicacao(){
        this.dataAcompanhamento = dataAcompanhamento.plusHours(intervaloHora);
    }
}
