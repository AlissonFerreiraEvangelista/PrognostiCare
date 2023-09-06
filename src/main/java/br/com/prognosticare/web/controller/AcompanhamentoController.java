package br.com.prognosticare.web.controller;


import br.com.prognosticare.domain.entity.acompanhamento.DtoAtualizaAcompanhamento;
import br.com.prognosticare.domain.entity.acompanhamento.DtoCadastroAcompanhamento;
import br.com.prognosticare.domain.entity.acompanhamento.DtoDetalheAcompanhamento;
import br.com.prognosticare.domain.service.AcompanhamentoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/to-accompany")
public class AcompanhamentoController {

    @Autowired
    AcompanhamentoService acompanhamentoService;


    @PutMapping("/update")
    public ResponseEntity<DtoDetalheAcompanhamento>atualizaAcompanhamento(@RequestBody @Valid DtoAtualizaAcompanhamento dto){
        var acompanhamento = acompanhamentoService.getReferenceById(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new DtoDetalheAcompanhamento(acompanhamento));
    }

    @PostMapping("/save/{id}")
    @ApiResponse(description = "Salva Acompanhamentos de uma pessoa")
    public ResponseEntity<DtoDetalheAcompanhamento> cadastraAcompanhamento(@PathVariable(value = "id") @Valid UUID id, @RequestBody @Valid DtoCadastroAcompanhamento dto){
        var acompanhamento = acompanhamentoService.adicionaAcompanhamento(id, dto);
        if(acompanhamento == null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(acompanhamento);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<DtoDetalheAcompanhamento>> listaAcompanhamentos(@PathVariable @Valid UUID id){
        var listaAcompanhamentos = acompanhamentoService.listaAcompanhamentos(id);
        if(listaAcompanhamentos.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(listaAcompanhamentos);
    }
}
