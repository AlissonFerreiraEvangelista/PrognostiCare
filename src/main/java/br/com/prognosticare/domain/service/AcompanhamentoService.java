package br.com.prognosticare.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.prognosticare.domain.entity.acompanhamento.*;
import br.com.prognosticare.domain.entity.agenda.DtoStatus;
import br.com.prognosticare.domain.entity.dto.DtoData;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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

    @Transactional
    public DtoDetalheAcompanhamento adicionaAcompanhamento(UUID id, DtoCadastroAcompanhamento dto) {

        var pessoa = pessoaService.get(id).orElse(null);

        if (pessoa != null) {
            var acompanhamento = new AcompanhamentoEntity(dto);
            acompanhamento.setPessoa(pessoa);

            if (acompanhamento.getIntervaloHora() == null) {
                acompanhamento.setIntervaloHora(0);
            }

            save(acompanhamento);
            pessoa.getAcompanhamentos().add(acompanhamento);

            return new DtoDetalheAcompanhamento(acompanhamento);
        } else {
            return null;
        }
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
        if (acompanhamento != null) {
            acompanhamento.setStatusEvento(dto.statusEvento());
            acompanhamentoRepository.save(acompanhamento);
            return new DtoDetalheAcompanhamento(acompanhamento);
        }

        return null;
    }

    public List<DtoDetalheAcompanhamento> listaAcompanhamentoData(UUID id, LocalDateTime dataInicial, String filtro) {
        var pessoa = pessoaService.get(id).orElse(null);

        List<DtoDetalheAcompanhamento> acompanhamentos;

        if (pessoa == null || dataInicial == null) {
            throw new ValidacaoException("Parâmetros inválidos para listaAcompanhamentoData");
        }

        if (filtro.equalsIgnoreCase("maior") && dataInicial != null) {

            acompanhamentos = acompanhamentoRepository.findByDataAcompanhamentoMaior(pessoa,
                    dataInicial.plusDays(1));

        } else if (filtro.equalsIgnoreCase("menor") && dataInicial != null) {

            acompanhamentos = acompanhamentoRepository.findByDataAcompanhamentoMenor(pessoa,
                    dataInicial.minusDays(1));

        } else if (filtro.equalsIgnoreCase("igual") && dataInicial != null) {

            acompanhamentos = acompanhamentoRepository.findByDateBetween(pessoa,
                    dataInicial.minusHours(4), dataInicial.plusHours(5));
        } else {
            throw new ValidacaoException("Erro no Filtro listaAcompanhamentoData");
        }

        if (acompanhamentos.isEmpty()) {
            return null;
        }

        return acompanhamentos;
    }

    public List<DtoDetalheAcompanhamento> listarIntervaloData(UUID id, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        var pessoa = pessoaService.get(id).orElse(null);
        if(pessoa!=null){
            var acompanhamentos = acompanhamentoRepository.findByDateBetween(pessoa, dataInicial, dataFinal);
            return acompanhamentos;
        }
        return null;
    }
}
