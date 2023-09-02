package br.com.prognosticare.web.controller;


import br.com.prognosticare.domain.entity.acompanhamento.AcompanhamentoEntity;
import br.com.prognosticare.domain.entity.acompanhamento.DtoAtualizaAcompanhamento;
import br.com.prognosticare.domain.entity.acompanhamento.DtoCadastroAcompanhamento;
import br.com.prognosticare.domain.entity.acompanhamento.DtoDetalheAcompanhamento;
import br.com.prognosticare.domain.service.AcompanhamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/to-accompany")
public class AcompanhamentoController {

    @Autowired
    AcompanhamentoService acompanhamentoService;

    @PostMapping("/save")
    public ResponseEntity<DtoDetalheAcompanhamento>cadastraAcompanhamento(@RequestBody @Valid DtoCadastroAcompanhamento dto){
        var acompanhamento = new AcompanhamentoEntity(dto);
        var acompanhamentoSalvo = acompanhamentoService.save(acompanhamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoDetalheAcompanhamento(acompanhamentoSalvo));
    }

    @PutMapping("/update")
    public ResponseEntity<DtoDetalheAcompanhamento>atualizaAcompanhamento(@RequestBody @Valid DtoAtualizaAcompanhamento dto){
        var acompanhamento = acompanhamentoService.getReferenceById(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new DtoDetalheAcompanhamento(acompanhamento));
    }
}
