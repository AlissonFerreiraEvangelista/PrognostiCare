package br.com.prognosticare.domain.service;

import java.util.ArrayList;
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
import br.com.prognosticare.web.controller.DtoTokenFCM;
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
        var pessoa = pessoaRepository.findById(pessoa_id);
        if(pessoa == null){
            throw new ValidacaoException("Pessoa não encontada!!");
        }
        return pessoa;
    }

    
    @Transactional
    public PessoaEntity getReferenceById(DtoAtualizaPessoa dto) {
        var pessoa = pessoaRepository.getReferenceById(dto.pessoaId());
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


    public DtoDetalheDependente adicionarDependente(UUID id, DtoCadastroDependente dto) {
        var pessoa = get(id).orElse(null);
        if(pessoa ==null){
            throw new ValidacaoException("Pessoa não encontrada");
        }

        var dependente = new PessoaEntity(dto);
        dependente.setResponsavel(pessoa);
        dependente.setContato(pessoa.getContato());
        dependente.setTipoResponsavel(false);
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

    
    public void setTokenFCM(UUID id,String tokenFCM) {
        var pessoa = getReferenceById(id);
        pessoa.setTokenFCM(tokenFCM);
        pessoaRepository.save(pessoa);
         

    }


    public PessoaEntity getReferenceById(DtoAtualizaDependente dto) {
        var dependent = pessoaRepository.getReferenceById(dto.pessoaId());
        if(dependent == null){
            throw new ValidacaoException("Dependente não encontrado!");
        }
        dependent.atualizarDependente(dto);
        return dependent;
    }

    public Boolean inativaPessoa(UUID id){
        var pessoa = getReferenceById(id);
        pessoa.setAtivo(false);
        pessoaRepository.save(pessoa);
        return true;
    }


    public List<DtoProfile> findDtoProfilesByDependente(UUID responsavelId) {
    List<DtoProfileProjection> projections = pessoaRepository.findByDependente(responsavelId);
    List<DtoProfile> dtoProfiles = new ArrayList<>();

    for (DtoProfileProjection projection : projections) {
        DtoProfile dtoProfile = new DtoProfile(
            projection.getPessoaId(),
            projection.getNome(),
            projection.getAtivo(),
            projection.getTipoResponsavel()
        );
        dtoProfiles.add(dtoProfile);
    }

    return dtoProfiles;
}


    

   
}
