package br.com.prognosticare.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prognosticare.domain.entity.vacina.DtoDetalheVacina;
import br.com.prognosticare.domain.entity.vacina.TipoVacina;
import br.com.prognosticare.domain.service.VacinaService;
import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("vaccines")
public class VacinasController {

    @Autowired
    VacinaService vService;

    @GetMapping(value="/list/{tipoVacina}")
    public ResponseEntity<List<DtoDetalheVacina>> getMethodName(@PathVariable(value = "tipoVacina") @Valid TipoVacina tipoVacina) {

        var vacinas = vService.findAllByTipoVacina(tipoVacina);
        return ResponseEntity.status(HttpStatus.OK).body(vacinas);
    }
    
    
}
