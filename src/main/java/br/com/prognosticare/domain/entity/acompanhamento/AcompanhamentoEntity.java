package br.com.prognosticare.domain.entity.acompanhamento;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import br.com.prognosticare.domain.enums.*;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAcompanhamento tipoAcompanhamento;

    private String medicacao;

    @Enumerated(EnumType.STRING)
    private Status statusEvento;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a")
    private LocalDateTime dataAcompanhamento;

    private Integer intervaloHora;

    @Enumerated(EnumType.STRING)
    private TipoTemporarioControlado tipoTemporarioControlado;

    @Column(columnDefinition = "TEXT")
    private String prescricaoMedica;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;

    public AcompanhamentoEntity(DtoCadastroAcompanhamento dto) {
        this.tipoAcompanhamento = dto.tipoAcompanhamento();
        this.medicacao = dto.medicacao();
        this.statusEvento = Status.ABERTO;
        this.dataAcompanhamento = dto.dataAcompanhamento();
        this.intervaloHora = dto.intervaloHora();
        this.tipoTemporarioControlado = dto.tipoTemporarioControlado();
        this.prescricaoMedica = dto.prescricaoMedica();
    }


    public void atualizaProxaMedicacao(){
        this.dataAcompanhamento = dataAcompanhamento.plusHours(intervaloHora);
    }

    public void atualizaInformacao(DtoAtualizaAcompanhamento dados) {
        Optional.ofNullable(dados.tipoAcompanhamento()).ifPresent(this::setTipoAcompanhamento);
        Optional.ofNullable(dados.intervaloHora()).ifPresent(this::setIntervaloHora);
        Optional.ofNullable(dados.medicacao()).ifPresent(this::setMedicacao);
        Optional.ofNullable(dados.statusEvento()).ifPresent(this::setStatusEvento);
        Optional.ofNullable(dados.dataAcompanhamento()).ifPresent(this::setDataAcompanhamento);
        Optional.ofNullable(dados.intervaloHora()).ifPresent(this::setIntervaloHora);
        Optional.ofNullable(dados.tipoTemporarioControlado()).ifPresent(this::setTipoTemporarioControlado);
        Optional.ofNullable(dados.prescricaoMedica()).ifPresent(this::setPrescricaoMedica);

    }
}
