package br.com.prognosticare.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prognosticare.domain.entity.usuario.DtoAutenticacao;
import br.com.prognosticare.domain.entity.usuario.DtoToken;
import br.com.prognosticare.domain.entity.usuario.Usuario;
import br.com.prognosticare.domain.repository.PessoaRepository;
import br.com.prognosticare.domain.service.TokenService;
import jakarta.validation.Valid;

@RequestMapping("/login")
@RestController
public class AutenticacaoController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuaLogin(@RequestBody @Valid DtoAutenticacao dto){
        var autenticacaoToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var autenticado = authenticationManager.authenticate(autenticacaoToken);
        var tokenJWT = tokenService.gerarToken((Usuario) autenticado.getPrincipal());
        var pessoaAutenticada = (Usuario) autenticado.getPrincipal();
        var pessoa = pessoaRepository.pesquisaPorIdUsuario(pessoaAutenticada.getUser_id());
        return ResponseEntity.ok(new DtoToken(tokenJWT, pessoa.getPessoa_id()));
    }



}
