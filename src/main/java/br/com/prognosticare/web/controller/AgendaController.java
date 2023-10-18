package br.com.prognosticare.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.prognosticare.domain.entity.acompanhamento.DtoDetalheAcompanhamento;
import br.com.prognosticare.domain.entity.agenda.DtoCadastroAgenda;
import br.com.prognosticare.domain.entity.agenda.DtoDetalheAgenda;
import br.com.prognosticare.domain.entity.agenda.DtoStatus;
import br.com.prognosticare.domain.entity.dto.DtoData;
import br.com.prognosticare.domain.enums.Especialidade;
import br.com.prognosticare.domain.enums.TipoExame;
import br.com.prognosticare.domain.service.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/to-scheduling")
@CrossOrigin("*")
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
    
    @GetMapping("/specialty/{especialidade}")
    @Operation(summary= "Lista por Especialidade")
    public ResponseEntity<List<DtoDetalheAgenda>> listaPorEspecialidade(@PathVariable(value = "especialidade") @Valid Especialidade especialidade ){

        var especialidades = agendaService.getEspecialidades(especialidade);
        if(especialidades.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(especialidades) ;
    }

    @GetMapping("/exam/{consulta}")
    @Operation(summary= "Lista por Consultas")
    public ResponseEntity<List<DtoDetalheAgenda>> listaPorConsultas(@PathVariable(value = "consulta") @Valid TipoExame tipoExame ){

        var consultas = agendaService.getTipoConsulta(tipoExame);
        if(consultas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(consultas) ;
    }

    @PutMapping("/update-status/{id}")
    @Operation(summary= "Atualiza o status da agenda")
    public ResponseEntity<DtoDetalheAgenda> atualizaStatus(@PathVariable(value = "id") UUID id,  @Valid @RequestBody DtoStatus dto){
        var agenda = agendaService.atualizaStatus(id, dto);
        if(agenda ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(agenda);
    }

    @GetMapping("/list-day/{id}")
    @Operation(summary= "Lista os Agendamentos de uma pessoa com base na data")
    public ResponseEntity<List<DtoDetalheAgenda>>listaAgendamentoData(@PathVariable (value = "id") UUID id, @RequestBody @Valid DtoData dto, @RequestParam String filtro){
        var agendamentos = agendaService.listaAgendamentoData(id, dto, filtro);
        if(agendamentos==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(agendamentos);
    }

    @GetMapping("/between-days/{id}")
    @Operation(summary= "Lista os Agendamentos no intervalo de datas")
    public ResponseEntity<List<DtoDetalheAgenda>> listarEntredatas(){
        return null;
    }

}
