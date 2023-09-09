package br.com.prognosticare.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.prognosticare.domain.entity.acompanhamento.DtoAtualizaAcompanhamento;
import br.com.prognosticare.domain.entity.acompanhamento.DtoDetalheAcompanhamento;
import br.com.prognosticare.domain.entity.agenda.DtoCadastroAgenda;
import br.com.prognosticare.domain.entity.agenda.DtoDetalheAgenda;
import br.com.prognosticare.domain.service.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/to-scheduling")
public class AgendaController {

    @Autowired
    AgendaService agendaService;

    @PostMapping("/save/{pessoa_id}")
    @Operation(summary= "Salva o agendamento de uma pessoa")
    public ResponseEntity<DtoDetalheAgenda> cadastraAgenda(@PathVariable (value = "pessoa_id") @Valid UUID id, @RequestBody @Valid DtoCadastroAgenda dto){

        var agenda = agendaService.adicionaAgenda(id, dto);
        if(agenda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(agenda);
    }

    @PutMapping("/update")
    @Operation(summary= "Atualiza a Agenda de uma pessoa")
    public ResponseEntity<DtoDetalheAgenda>atualizaAgenda(@RequestBody @Valid DtoDetalheAgenda dto){
        var agenda = agendaService.getReferenceById(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new DtoDetalheAgenda(agenda));
    }


    @GetMapping("/list/{pessoa_id}")
    @Operation(summary= "Lista os Agendamentos de uma pessoa")
    public ResponseEntity<List<DtoDetalheAgenda>> listaAgendamentos(@PathVariable(value = "pessoa_id") @Valid UUID id){
        var listaAgenda = agendaService.listaAgendamentos(id);
        if(listaAgenda.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(listaAgenda);
    }
    
}
