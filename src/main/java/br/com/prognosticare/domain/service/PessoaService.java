package br.com.prognosticare.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.prognosticare.domain.entity.pessoa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.prognosticare.domain.repository.PessoaRepository;
import br.com.prognosticare.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
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

    public PessoaEntity getReferenceById(DtoAtualizaPessoa dto) {
        var pessoa = pessoaRepository.getReferenceById(dto.pessoa_id());
        if(pessoa == null){
            throw  new ValidacaoException("Pessoa não encontrada!!");
        }
        pessoa.atualizarInformacoes(dto);
        return pessoa;
    }

    public PessoaEntity getReferenceById(UUID id) {
        var pessoa = pessoaRepository.getReferenceById(id);
        if(pessoa == null){
            throw  new ValidacaoException("Pessoa não encontrada!!");
        }
        return pessoa;
    }

    public void findByEmail(String email) {
        var pessoa = pessoaRepository.findByEmail(email);
        if(pessoa!=null){
            pessoa.setPassword(pEncoder.encode(passwordDefault));

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


    public DtoDetalheDependente adicionarDependente(@Valid UUID id, @Valid DtoCadastroDependente dto) {
        var pessoa = get(id).orElse(null);
        if(pessoa ==null){
            throw new ValidacaoException("Pessoa não encontrada");
        }

        var dependente = new PessoaEntity(dto);
        dependente.setResponsavel(pessoa);
        dependente.setContato(pessoa.getContato());
        pessoa.getDependente().add(dependente);

        pessoaRepository.save(dependente);
        

        return new DtoDetalheDependente(dependente);
    }


	public List<DtoDependente> listarDependentes(@Valid UUID id) {
        var pessoaResponsavel = get(id).orElse(null);
        if(pessoaResponsavel ==null){
            throw new ValidacaoException("Dependente não encontrados!!");
        }
        List<PessoaEntity> dependentes = pessoaResponsavel.getDependente();

        List<DtoDependente> dtoDependentes = dependentes.stream()
                .map(DtoDependente::new)
                .collect(Collectors.toList());
		return dtoDependentes;
	}

   
}
