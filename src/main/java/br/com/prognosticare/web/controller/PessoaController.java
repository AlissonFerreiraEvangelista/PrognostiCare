package br.com.prognosticare.web.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.prognosticare.domain.entity.pessoa.DtoAtualizaPessoa;
import br.com.prognosticare.domain.entity.pessoa.DtoCadastroPessoa;
import br.com.prognosticare.domain.entity.pessoa.DtoDetalhePessoa;
import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import br.com.prognosticare.domain.service.PessoaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(description = "Cadastro Inicial de uma pessoa")
    @Transactional
    public ResponseEntity cadastrarPessoa(@RequestBody @Valid DtoCadastroPessoa dto, UriComponentsBuilder uriBuilder) {
        var pessoa = new PessoaEntity(dto);
        pessoaService.save(pessoa);
        var uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getPessoa_id()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhePessoa(pessoa));

    }

    @PutMapping("/update")
    @ApiResponse(description = "Atualiza as informações da Pessoa")
    @Transactional
    public ResponseEntity atualizaPessoa(@RequestBody @Valid DtoAtualizaPessoa dto) {
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

}
