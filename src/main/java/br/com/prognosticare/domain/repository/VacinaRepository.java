package br.com.prognosticare.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.prognosticare.domain.entity.vacina.DtoDetalheVacina;
import br.com.prognosticare.domain.entity.vacina.TipoVacina;
import br.com.prognosticare.domain.entity.vacina.VacinaEntity;

@Repository
public interface VacinaRepository extends JpaRepository<VacinaEntity, Long>{


    @Query(value = "SELECT v FROM VacinaEntity v WHERE v.tipoVacina = :tipoVacina")
    List<DtoDetalheVacina> findAllByTipoVacina(@Param("tipoVacina") TipoVacina tipoVacina);
    
}
