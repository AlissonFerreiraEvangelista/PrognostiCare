package br.com.prognosticare.web.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.prognosticare.domain.entity.usuario.*;
import br.com.prognosticare.domain.repository.UsuarioRepository;
import br.com.prognosticare.domain.service.EmailService;
import br.com.prognosticare.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/register")
//@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class UsuarioController {

    private final EmailService emailService;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;


    @PutMapping("/update")
    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponse(description = "Atualiza email e senha do usuário - Perfil de USER")
    public ResponseEntity<?> update(@RequestBody @Valid DtoSenhaEmail dtoSenhaEmail) {
  
        var usuarioAtualizado = usuarioRepository.getReferenceById(dtoSenhaEmail.user_id());
        return ResponseEntity.status(HttpStatus.OK).body(new DtoDetalheEmailSenha(usuarioAtualizado));
    }

    @PostMapping("/public/forgot-password")
    @ApiResponse(description = "Envia email para recuperação de senha - Perfil de USER")
    public void forgotPassword(@RequestBody @Valid DtoSenhaRestInput dto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(dto.email());
        String senhaDefault = "abcdef";
        usuarioOptional.ifPresent(usuario -> {
            usuario.setPassword(senhaDefault);
            usuarioService.saveUser(usuario);
            try {
                emailService.enviarEmailRecuperacaoSenha(usuario, senhaDefault);
            } catch (MailException e) {
                log.error("Erro ao enviar Email", e);
                e.printStackTrace();
            }
           
        });
         System.out.println(senhaDefault);

    }


     /* 
        @PostMapping("/public/change-password/{token}")
        @ApiResponse(description = "Link com o token para troca da senha - Perfil de USER")
        public void changePasswordTeste(@PathVariable String token, @RequestBody @Valid DtoSenha dto) {

        try {
            usuarioService.trocaSenha(dto.password(), token);

        } catch (Exception e) {
            log.error("Erro ao alterar a senha usando token", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
     */
 

}
