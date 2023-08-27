package br.com.prognosticare.domain.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import br.com.prognosticare.domain.repository.PessoaRepository;
import br.com.prognosticare.infra.exception.ValidacaoException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {
    
    private final PessoaRepository pessoaRepository;
    private final PasswordEncoder pEncoder;

    @Autowired
    EmailService emailService;

    @Value("${PASSWORD_DEFAULT}")
    String passwordDefault;

    @Transactional
    public PessoaEntity save(PessoaEntity pessoa) {

        var verificaPessoa = pessoaRepository.findByEmail(pessoa.getEmail());
        if(verificaPessoa != null){
            throw new ValidacaoException("Email já cadastrado");
        }       
        pessoa.setPassword(pEncoder.encode(pessoa.getPassword()));
        return pessoaRepository.save(pessoa);
    }

    
    public Optional<PessoaEntity> get(UUID pessoa_id){
        return pessoaRepository.findById(pessoa_id);
    }

    public PessoaEntity getReferenceById(UUID pessoa_id) {
        return pessoaRepository.getReferenceById(pessoa_id);
    }

    public void findByEmail(String email) {
        var pessoa = pessoaRepository.findByEmail(email);
        if(pessoa!=null){
            pessoa.setPassword(passwordDefault);
            pessoaRepository.save(pessoa);
            emailService.enviarEmailRecuperacaoSenha(pessoa, passwordDefault);

        }else{
            throw new ValidacaoException("Problema no e-mail");
        }
     
        
    }

    @Transactional
    public PessoaEntity savePassword(UUID id, String password) {
        var pessoa = getReferenceById(id);

        if(pessoa != null){
            pessoa.setPassword(pEncoder.encode(password));
            return pessoaRepository.save(pessoa);
        } 
        return null;      
        
    }

   
}
