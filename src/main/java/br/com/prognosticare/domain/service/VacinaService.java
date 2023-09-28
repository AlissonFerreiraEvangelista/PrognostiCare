package br.com.prognosticare.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prognosticare.domain.entity.vacina.DtoDetalheVacina;
import br.com.prognosticare.domain.entity.vacina.TipoVacina;
import br.com.prognosticare.domain.repository.VacinaRepository;

@Service
public class VacinaService {

    @Autowired
    VacinaRepository vRepository;


    public List<DtoDetalheVacina> findAllByTipoVacina(TipoVacina tipoVacina){
        var vacinas = vRepository.findAllByTipoVacina(tipoVacina);
        return vacinas;
    }
    
}
