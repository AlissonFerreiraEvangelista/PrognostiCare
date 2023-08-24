package br.com.prognosticare.web.controller;

import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
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
import br.com.prognosticare.domain.entity.pessoa.DtoCadastroPessoa;
import br.com.prognosticare.domain.entity.pessoa.DtoDetalhePessoa;
import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import br.com.prognosticare.domain.repository.PessoaRepository;
import br.com.prognosticare.domain.service.EmailService;
import br.com.prognosticare.domain.service.PessoaService;
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
    public ResponseEntity<?> cadastrarPessoa(@RequestBody @Valid DtoCadastroPessoa dto, UriComponentsBuilder uriBuilder) {
        var pessoa = new PessoaEntity(dto);
        pessoaService.save(pessoa);
        var uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getPessoa_id()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhePessoa(pessoa));

    }

    @PutMapping("/update")
    @ApiResponse(description = "Atualiza as informações da Pessoa")
    @Transactional
    public ResponseEntity<?> atualizaPessoa(@RequestBody @Valid DtoAtualizaPessoa dto) {
        var pessoa = pessoaService.getReferenceById(dto.pessoa_id());
        pessoa.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DtoDetalhePessoa(pessoa));
    }

    @GetMapping("find/{id}")
    @ApiResponse(description = "Encontra uma pessoa por ID")
    public ResponseEntity<Object> encontraPorID(@PathVariable @Valid UUID id) {

        var pessoa = pessoaService.get(id).orElse(null);
        if (pessoa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new DtoDetalhePessoa(pessoa));
    }



    @PostMapping("/public/forgot-password")
    @ApiResponse(description = "Envia email para recuperação de senha")
    public void forgotPassword(@RequestBody @Valid DtoSenhaRestInput dto) {

        Optional<PessoaEntity> pessoaOptional = pessoaService.findByEmail(dto.email());
        String senhaDefault = "abcdef";
        pessoaOptional.ifPresent(pessoa -> {
            pessoa.setPassword(senhaDefault);
            pessoaService.save(pessoa);
            try {
                emailService.enviarEmailRecuperacaoSenha(pessoa, senhaDefault);
            } catch (MailException e) {
                log.error("Erro ao enviar Email", e);
                e.printStackTrace();
            }

        });

    }

    @PutMapping("/public/change-password/{id}")
    @ApiResponse(description = "Troca da senha")
    public ResponseEntity<?> changePassword(@PathVariable (value = "id") UUID id, @RequestBody @Valid DtoSenha dto) {
        var pessoa = pessoaRepository.getReferenceById(id);

        if (pessoa != null) {
            pessoa.setPassword(dto.password());
            pessoaService.save(pessoa);
            return ResponseEntity.status(HttpStatus.OK).body("Senha Alterada com Sucesso!");

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
    }
}
