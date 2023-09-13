package br.com.prognosticare.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.prognosticare.domain.entity.dto.DtoSenha;
import br.com.prognosticare.domain.entity.dto.DtoSenhaRestInput;
import br.com.prognosticare.domain.entity.pessoa.DtoAtualizaPessoa;
import br.com.prognosticare.domain.entity.pessoa.DtoCadastroDependente;
import br.com.prognosticare.domain.entity.pessoa.DtoCadastroPessoa;
import br.com.prognosticare.domain.entity.pessoa.DtoDependente;
import br.com.prognosticare.domain.entity.pessoa.DtoDetalheDependente;
import br.com.prognosticare.domain.entity.pessoa.DtoDetalhePessoa;
import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import br.com.prognosticare.domain.repository.PessoaRepository;
import br.com.prognosticare.domain.service.EmailService;
import br.com.prognosticare.domain.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("register-person")
@CrossOrigin("*")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    EmailService emailService;

    @PostMapping("/save")
    @Operation(summary= "Cadastro Inicial de uma pessoa")
    @Transactional
    public ResponseEntity<DtoDetalhePessoa> cadastrarPessoa(@RequestBody @Valid DtoCadastroPessoa dto,
            UriComponentsBuilder uriBuilder) {
        var pessoa = new PessoaEntity(dto);
        pessoaService.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoDetalhePessoa(pessoa));

    }

    @PutMapping("/update")
    @Operation(summary= "Atualiza as informações da Pessoa")
    @Transactional
    public ResponseEntity<DtoDetalhePessoa> atualizaPessoa(@RequestBody @Valid DtoAtualizaPessoa dto) {
        var pessoa = pessoaService.getReferenceById(dto);
        pessoa.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DtoDetalhePessoa(pessoa));
    }

    @GetMapping("find/{pessoa_id}")
    @Operation(summary= "Encontra uma pessoa por ID")
    public ResponseEntity<DtoDetalhePessoa> encontraPorID(@PathVariable(value = "pessoa_id") @Valid UUID id) {

        var pessoa = pessoaService.get(id).orElse(null);
        if (pessoa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new DtoDetalhePessoa(pessoa));
    }

    @PostMapping("/public/forgot-password")
    @Operation(summary= "Envia email para recuperação de senha")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid DtoSenhaRestInput dto) {

        pessoaService.findByEmail(dto.email());
        return ResponseEntity.ok().body("Email enviado com Sucesso!!");
       
    }

    @PutMapping("/public/change-password/{pessoa_id}")
    @Operation(summary= "Troca da senha")
    public ResponseEntity<?> changePassword(@PathVariable(value = "pessoa_id") UUID id, @RequestBody @Valid DtoSenha dto) {

        var pessoa = pessoaService.savePassword(id, dto.password());

        if (pessoa.isEnabled()) {
            return ResponseEntity.status(HttpStatus.OK).body("Senha Alterada com Sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
    }

    @PostMapping("/add-dependent/{pessoa_id}")
    @Operation(summary= "Adiciona um dependente a uma pessoa")
    @Transactional
    public ResponseEntity<DtoDetalheDependente> adicionarDependente(
            @PathVariable (value = "pessoa_id") @Valid UUID id,
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
    @Operation(summary= "Lista os dependentes de uma pessoa responsável")
    public ResponseEntity<List<DtoDependente>> listarDependentes(@PathVariable (value = "id") @Valid UUID id) {

        var listaDependentes = pessoaService.listarDependentes(id);
       
        if (listaDependentes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaDependentes);
    }

}
