package br.com.prognosticare.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/new")
    @ApiResponse(description = "Cadastra um usuário - Perfil de ADMIN")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid UsuarioDto usuarioDto) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        Usuario user = usuarioService.findUsuarioEmail(usuario.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.saveUser(usuario));
        }
        return ResponseEntity.status(HttpStatus.OK).body("Emails already exist");
    }

    @GetMapping("/findall")
    @ApiResponse(description = "Retorna uma lista de usuários - Perfil de ADMIN")
    public ResponseEntity<List<Usuario>> findAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAllByUser());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiResponse(description = "Atualiza um usuário - Perfil de USER")
    public ResponseEntity<?> update(@RequestBody Usuario usuario) {
        if (usuario.getUser_id() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não informado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.saveAndFlushUser(usuario));
    }

    @PostMapping("/public/forgot-password")
    public void forgotPassword(@RequestBody @Valid DtoSenhaRestInput dto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(dto.email());
        usuarioOptional.ifPresent(usuario -> {
            String token = usuarioService.gerarToken(usuario);
            try {
                emailService.enviarEmailRecuperacaoSenha(usuario, token);
            } catch (MailException e) {
                log.error("Erro ao enviar Email", e);
                e.printStackTrace();
            }
            System.out.println(token);
        });

    }

    @PostMapping("/public/change-password")
    public void changePassword(@RequestBody @Valid DtoSenhaAlteradaInput dtoSenhaAlteradaInput) {

        try {
            usuarioService.trocaSenha(dtoSenhaAlteradaInput.password(), dtoSenhaAlteradaInput.tokenResetSenha());

        } catch (Exception e) {
            log.error("Erro ao alterar a senha usando token", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

}
