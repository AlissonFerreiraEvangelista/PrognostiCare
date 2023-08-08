package br.com.prognosticare.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prognosticare.domain.entity.role.RoleEntity;
import br.com.prognosticare.domain.entity.usuario.Usuario;
import br.com.prognosticare.domain.entity.usuario.UsuarioDto;
import br.com.prognosticare.domain.service.AtualizarRolesRequest;
import br.com.prognosticare.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/register")
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/new")
    @ApiResponse(description = "Cadastra um usuário - Perfil de ADMIN")
    public ResponseEntity<Object> registerUser(@RequestBody UsuarioDto usuarioDto) {
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

    @PutMapping("/{id}/atualizar-roles")
    public ResponseEntity<String> atualizaRoles(@PathVariable("id") UUID userId,
            @RequestBody AtualizarRolesRequest request) {
        if (request != null) {
            boolean updated = usuarioService.atualizarRoles(userId, request.roles());
            if (updated) {
                return ResponseEntity.ok("Roles Atualizada com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Roles null");
        }

    }
}
