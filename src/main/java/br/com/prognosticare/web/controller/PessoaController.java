package br.com.prognosticare.web.controller;

import java.util.List;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.prognosticare.domain.entity.dto.*;
import br.com.prognosticare.domain.entity.pessoa.*;
import br.com.prognosticare.domain.repository.PessoaRepository;
import br.com.prognosticare.domain.service.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("register-person")
@SecurityRequirement(name = "bearer-key")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    EmailService emailService;

    @PostMapping("/save")
    @ApiResponse(description = "Cadastro Inicial de uma pessoa")
    @Transactional
    public ResponseEntity<DtoDetalhePessoa> cadastrarPessoa(@RequestBody @Valid DtoCadastroPessoa dto,
            UriComponentsBuilder uriBuilder) {
        var pessoa = new PessoaEntity(dto);
        pessoaService.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoDetalhePessoa(pessoa));

    }

    @PutMapping("/update")
    @ApiResponse(description = "Atualiza as informações da Pessoa")
    @Transactional
    public ResponseEntity<DtoDetalhePessoa> atualizaPessoa(@RequestBody @Valid DtoAtualizaPessoa dto) {
        var pessoa = pessoaService.getReferenceById(dto);
        pessoa.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DtoDetalhePessoa(pessoa));
    }

    @GetMapping("find/{pessoa_id}")
    @ApiResponse(description = "Encontra uma pessoa por ID")
    public ResponseEntity<DtoDetalhePessoa> encontraPorID(@PathVariable @Valid UUID id) {

        var pessoa = pessoaService.get(id).orElse(null);
        if (pessoa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new DtoDetalhePessoa(pessoa));
    }

    @PostMapping("/public/forgot-password")
    @ApiResponse(description = "Envia email para recuperação de senha")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid DtoSenhaRestInput dto) {

        pessoaService.findByEmail(dto.email());
        return ResponseEntity.ok().body("Email enviado com Sucesso!!");
       
    }

    @PutMapping("/public/change-password/{pessoa_id}")
    @ApiResponse(description = "Troca da senha")
    public ResponseEntity<?> changePassword(@PathVariable(value = "pessoa_id") UUID id, @RequestBody @Valid DtoSenha dto) {

        var pessoa = pessoaService.savePassword(id, dto.password());

        if (pessoa.isEnabled()) {
            return ResponseEntity.status(HttpStatus.OK).body("Senha Alterada com Sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
    }

    @PostMapping("/add-dependent/{pessoa_id}")
    @ApiResponse(description = "Adiciona um dependente a uma pessoa")
    @Transactional
    public ResponseEntity<DtoDetalheDependente> adicionarDependente(
            @PathVariable @Valid UUID id,
            @RequestBody @Valid DtoCadastroDependente dto,
            UriComponentsBuilder uriBuilder) {
            
            var dependente = pessoaService.adicionarDependente(id, dto);

        if (dependente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      
        var uri = uriBuilder.path("/dependente/{id}").buildAndExpand(dependente.getClass()).toUri();
        return ResponseEntity.created(uri).body(dependente);
    }

    @GetMapping("/list-dependents/{id}")
    @ApiResponse(description = "Lista os dependentes de uma pessoa responsável")
    public ResponseEntity<List<DtoDependente>> listarDependentes(@PathVariable @Valid UUID id) {

        var listaDependentes = pessoaService.listarDependentes(id);
       
        if (listaDependentes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaDependentes);
    }

}
