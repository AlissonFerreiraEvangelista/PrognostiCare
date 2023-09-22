package br.com.prognosticare.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.prognosticare.domain.entity.vacina.DtoDetalheVacina;
import br.com.prognosticare.domain.entity.vacina.TipoVacina;
import br.com.prognosticare.domain.entity.vacina.VacinaEntity;

public interface VacinaRepository extends JpaRepository<VacinaEntity, Long>{



    List<DtoDetalheVacina> findAllByTipoVacina(@Param ("TipoVacina") TipoVacina tipoVacina);
    
}
