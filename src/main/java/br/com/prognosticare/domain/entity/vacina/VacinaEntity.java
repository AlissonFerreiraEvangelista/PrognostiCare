package br.com.prognosticare.domain.entity.vacina;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "tb_vacina")
@NoArgsConstructor
@AllArgsConstructor
public class VacinaEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TipoVacina TipoVacina;

    private String vacina;

    private String protecaoContra;

    private String composicao;

    private String esquemaBasico;

    private String reforco;

    private String idadeRecomendada;

    private String intervaloRecomendado;

    private String intervaloMinimo;
    
}
