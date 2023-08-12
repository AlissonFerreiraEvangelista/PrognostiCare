package br.com.prognosticare.domain.service;

import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.prognosticare.domain.entity.role.RoleEntity;
import br.com.prognosticare.domain.entity.usuario.SenhaTokenPublica;
import br.com.prognosticare.domain.entity.usuario.Usuario;
import br.com.prognosticare.domain.entity.usuario.UsuarioNotFoundExeption;
import br.com.prognosticare.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    private final PasswordEncoder pEncoder;

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

    @SneakyThrows
    public String gerarToken(Usuario usuario){
        KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();
        tokenService.setServerSecret(usuario.getPassword());
        tokenService.setServerInteger(12);
        tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
        Token token = tokenService.allocateToken(usuario.getEmail());

        return token.getKey();
    }
    
    @SneakyThrows
    public void trocaSenha(String novaSenha, String rawToken){
        SenhaTokenPublica senhaTokenPublica = lerSenhaPublica(rawToken);
        if(isExpired(senhaTokenPublica)) {
            throw new RuntimeException("Token expirado");
        }
                Usuario usuario = usuarioRepository.findByEmail(senhaTokenPublica.getEmail()).orElseThrow(UsuarioNotFoundExeption::new);
        KeyBasedPersistenceTokenService tokenService = this.getInstanceFor(usuario);
        tokenService.verifyToken(rawToken);

        usuario.setPassword(this.pEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);

    }

    private SenhaTokenPublica lerSenhaPublica(String rawToken) {

         String rawTokenDecoded = new String(Base64.getDecoder().decode(rawToken));
         String [] tokenParts = rawTokenDecoded.split(":");
         Long timestamp = Long.parseLong(tokenParts[0]);
         String email = tokenParts[2];
         return new SenhaTokenPublica(email, timestamp) ;
    }

     private boolean isExpired(SenhaTokenPublica publicData) {
        Instant createdAt = new Date(publicData.getCreateAtTimestamp()).toInstant();
        Instant now = new Date().toInstant();
        return createdAt.plus(Duration.ofMinutes(5)).isBefore(now);
    }

    private KeyBasedPersistenceTokenService getInstanceFor(Usuario usuario) throws Exception {
        KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();

        tokenService.setServerSecret(usuario.getPassword());
        tokenService.setServerInteger(16);
        tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
        return tokenService;
    }
}
