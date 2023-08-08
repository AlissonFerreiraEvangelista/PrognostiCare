package br.com.prognosticare.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.prognosticare.domain.entity.role.RoleEntity;
import br.com.prognosticare.domain.entity.usuario.Usuario;
import br.com.prognosticare.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public Usuario findUsuarioEmail(String email){
        return usuarioRepository.findUsuarioByEmail(email);
    }

    
    @Transactional
    public Usuario saveUser(Usuario usuario){
       usuario.setPassword(passwordEncoder().encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);

    }

    public List<Usuario> findAllByUser() {
        return usuarioRepository.findAll();
    }
    @Transactional
    public Usuario saveAndFlushUser(Usuario usuario) {
        usuario.setPassword(passwordEncoder().encode(usuario.getPassword()));
        return usuarioRepository.saveAndFlush(usuario);
    }

    
    public boolean atualizarRoles(UUID userId, List<RoleEntity> roles) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(userId);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setRoles(roles);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }
}
