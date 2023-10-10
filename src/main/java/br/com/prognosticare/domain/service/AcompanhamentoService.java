package br.com.prognosticare.domain.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.prognosticare.domain.entity.acompanhamento.*;
import br.com.prognosticare.domain.entity.agenda.DtoStatus;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import br.com.prognosticare.domain.enums.Status;
import br.com.prognosticare.domain.repository.AcompanhamentoRepository;
import br.com.prognosticare.infra.exception.ValidacaoException;

@Service
public class AcompanhamentoService {

    @Autowired
    AcompanhamentoRepository acompanhamentoRepository;

    @Autowired
    PessoaService pessoaService;

    

    public List<AcompanhamentoEntity> findAll() {
        List<AcompanhamentoEntity> acompanhamentos = acompanhamentoRepository.findAll();

        if (acompanhamentos.isEmpty()) {
            throw new ValidacaoException("Não tem nenhum acompanhamento!");
        }
        return acompanhamentos;
    }

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public AcompanhamentoEntity save(AcompanhamentoEntity acompanhamento) {
        return acompanhamentoRepository.saveAndFlush(acompanhamento);
    }

    public AcompanhamentoEntity getReferenceById(DtoAtualizaAcompanhamento dto) {
        var acompanhamento = acompanhamentoRepository.getReferenceById(dto.id());
        if (acompanhamento == null) {
            throw new ValidacaoException("Não encontrado");
        }
        acompanhamento.atualizaInformacao(dto);
        return acompanhamentoRepository.saveAndFlush(acompanhamento);
    }

    public Optional<AcompanhamentoEntity> get(UUID id) {
        var acompanhamento = acompanhamentoRepository.findById(id);
        if (!acompanhamento.isPresent()) {
            throw new ValidacaoException("Acompanhamento não encontrado!!");
        }
        return acompanhamento;
    }

    public DtoDetalheAcompanhamento adicionaAcompanhamento(UUID id, DtoCadastroAcompanhamento dto) {
        var pessoa = pessoaService.get(id).orElse(null);
        var acompanhamento = new AcompanhamentoEntity(dto);
        acompanhamento.setPessoa(pessoa);
        pessoa.getAcompanhamentos().add(acompanhamento);
        save(acompanhamento);
        return new DtoDetalheAcompanhamento(acompanhamento);
    }

    public List<DtoDetalheAcompanhamento> listaAcompanhamentos(UUID id) {
        var pessoa = pessoaService.get(id).orElse(null);
        var acompanhamentos = acompanhamentoRepository.findByAcompanhamentoEntityWherePessoaEntity(pessoa);
        return acompanhamentos;
    }

    @Transactional
    public List<AcompanhamentoEntity> findAllByStatusEventoAberto() {

        return acompanhamentoRepository.findAllByStatusEvento();
    }

    public DtoDetalheAcompanhamento atualizaStatus(UUID id, @Valid DtoStatus dto) {
        var acompanhamento = acompanhamentoRepository.getReferenceById(id);
        if(acompanhamento !=null){
            acompanhamento.setStatusEvento(dto.statusEvento());
            acompanhamentoRepository.save(acompanhamento);
            return new DtoDetalheAcompanhamento(acompanhamento);
        }

        return null;
    }
}
