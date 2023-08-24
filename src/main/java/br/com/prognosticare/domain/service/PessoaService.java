package br.com.prognosticare.domain.service;

import java.util.Optional;
import java.util.UUID;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;
import br.com.prognosticare.domain.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {
    
    private final PessoaRepository pessoaRepository;
    private final PasswordEncoder pEncoder;

    @Transactional
    public PessoaEntity save(PessoaEntity pessoa) {
        pessoa.setPassword(pEncoder.encode(pessoa.getPassword()));
        return pessoaRepository.save(pessoa);
    }

    public Optional<PessoaEntity> get(UUID pessoa_id){
        return pessoaRepository.findById(pessoa_id);
    }

    public PessoaEntity getReferenceById(UUID pessoa_id) {
        return pessoaRepository.getReferenceById(pessoa_id);
    }

    public Optional<PessoaEntity> findByEmail(String email) {
        return pessoaRepository.findPessoaEntityByEmail(email);
    }

   
}
