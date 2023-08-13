package br.com.prognosticare.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.prognosticare.domain.entity.pessoa.DtoCadastroPessoa;
import br.com.prognosticare.domain.entity.pessoa.DtoDetalhePessoa;
import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import br.com.prognosticare.domain.service.PessoaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("register-person")
@SecurityRequirement(name = "bearer-key")
public class PessoaController {
    
    @Autowired
    PessoaService pessoaService;


    @PostMapping("/save")
    @Transactional
    public ResponseEntity cadastrarPessoa(@RequestBody @Valid DtoCadastroPessoa dto, UriComponentsBuilder uriBuilder){
        var pessoa = new PessoaEntity(dto);
        pessoaService.save(pessoa);
        var uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getPessoa_id()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhePessoa(pessoa));

    }
    
}