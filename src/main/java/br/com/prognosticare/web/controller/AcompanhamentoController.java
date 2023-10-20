package br.com.prognosticare.web.controller;


import br.com.prognosticare.domain.entity.acompanhamento.DtoAtualizaAcompanhamento;
import br.com.prognosticare.domain.entity.acompanhamento.DtoCadastroAcompanhamento;
import br.com.prognosticare.domain.entity.acompanhamento.DtoDetalheAcompanhamento;
import br.com.prognosticare.domain.entity.agenda.DtoStatus;
import br.com.prognosticare.domain.entity.dto.DtoData;
import br.com.prognosticare.domain.service.AcompanhamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/to-accompany")
@CrossOrigin("*")
public class AcompanhamentoController {

    @Autowired
    AcompanhamentoService acompanhamentoService;

    @PostMapping("/save/{pessoa_id}")
    @Operation(summary= "Salva Acompanhamentos de uma pessoa")
    public ResponseEntity<DtoDetalheAcompanhamento> cadastraAcompanhamento(@PathVariable(value = "pessoa_id") @Valid UUID id, @RequestBody @Valid DtoCadastroAcompanhamento dto){
        var acompanhamento = acompanhamentoService.adicionaAcompanhamento(id, dto);
        if(acompanhamento == null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(acompanhamento);
    }

    @PutMapping("/update")
    @Operation(summary= "Atualiza o Acompanhamentos de uma pessoa")
    public ResponseEntity<DtoDetalheAcompanhamento>atualizaAcompanhamento(@RequestBody @Valid DtoAtualizaAcompanhamento dto){
        var acompanhamento = acompanhamentoService.getReferenceById(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new DtoDetalheAcompanhamento(acompanhamento));
    }


    @GetMapping("/list/{pessoa_id}")
    @Operation(summary= "Lista os Acompanhamentos de uma pessoa")
    public ResponseEntity<List<DtoDetalheAcompanhamento>> listaAcompanhamentos(@PathVariable(value = "pessoa_id") @Valid UUID id){
        var listaAcompanhamentos = acompanhamentoService.listaAcompanhamentos(id);
        if(listaAcompanhamentos.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(listaAcompanhamentos);
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<DtoDetalheAcompanhamento>atualizaStatus(@PathVariable(value = "id") UUID id, @RequestBody @Valid DtoStatus dto){
        var acompanhamento = acompanhamentoService.atualizaStatus(id, dto);
        if(acompanhamento==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(acompanhamento);
    }

    @GetMapping("/list-day/{id}")
    @Operation(summary= "Lista os Acompanhamentos de uma pessoa com base na data")
    public ResponseEntity<List<DtoDetalheAcompanhamento>>listaAcompanhamentoData(@PathVariable (value = "id") UUID id, @RequestBody @Valid DtoData dto, @RequestParam String filtro){
        var acompanhamentos = acompanhamentoService.listaAcompanhamentoData(id, dto, filtro);
        if(acompanhamentos==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(acompanhamentos);
    }

    @GetMapping("/list-between/{id}")
    public ResponseEntity <List<DtoDetalheAcompanhamento>> listarIntervaloData(@PathVariable(value = "id") UUID id, @RequestBody @Valid DtoData dto){
        var acompanhamentos = acompanhamentoService.listarIntervaloData(id, dto);
        if(acompanhamentos==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(acompanhamentos);
    }


}
