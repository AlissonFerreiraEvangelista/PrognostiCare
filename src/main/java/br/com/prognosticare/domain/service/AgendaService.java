package br.com.prognosticare.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.prognosticare.domain.entity.agenda.AgendaEntity;
import br.com.prognosticare.domain.entity.agenda.DtoCadastroAgenda;
import br.com.prognosticare.domain.entity.agenda.DtoDetalheAgenda;
import br.com.prognosticare.domain.entity.agenda.DtoStatus;
import br.com.prognosticare.domain.enums.Especialidade;
import br.com.prognosticare.domain.enums.TipoExame;
import br.com.prognosticare.domain.repository.AgendaRepository;
import br.com.prognosticare.infra.exception.ValidacaoException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;



@Service
public class AgendaService {
    @Autowired
    PessoaService pessoaService;

    @Autowired
    AgendaRepository agendaRepository;

    public DtoDetalheAgenda adicionaAgenda(UUID id, DtoCadastroAgenda dto) {
        var pessoa = pessoaService.get(id).orElse(null);
        var agenda = new AgendaEntity(dto);
        agenda.setPessoa(pessoa);
        pessoa.getAgendas().add(agenda);
        save(agenda);

        return new DtoDetalheAgenda(agenda);
    }

   
    @Transactional
    public AgendaEntity save(AgendaEntity agenda) {
        return agendaRepository.save(agenda);
    }


    public AgendaEntity getReferenceById( DtoDetalheAgenda dto) {
        var agenda = agendaRepository.getReferenceById(dto.id());
        if (agenda == null){
            throw new ValidacaoException("Agenda não Encontrada!!");
        }
        agenda.atualizaInformacao(dto);
        return agendaRepository.saveAndFlush(agenda);
    }


    public List<DtoDetalheAgenda> listaAgendamentos(UUID id) {
        var pessoa = pessoaService.get(id).orElse(null);
        var agendas = agendaRepository.findByAgendaEntityWherePessoaEntity(pessoa);
        return agendas;
    }


    @Transactional
    public List<AgendaEntity> findAllByStatusEventoAberto() {
        return agendaRepository.findAllByStatusEventoAberto();
    }


    public List<DtoDetalheAgenda> getEspecialidades(Especialidade especialidade) {
        var especialidades = agendaRepository.findByAgendaEntityWhereEspecialista(especialidade);
        if(especialidades.isEmpty()){
            throw new ValidacaoException("Não tem agenda com essa Especialidade");
        }
        return especialidades;
    }


    public List<DtoDetalheAgenda> getTipoConsulta(TipoExame tipoExame) {
         var consultas = agendaRepository.findByAgendaEntityWhereTipoExame(tipoExame);
        if(consultas.isEmpty()){
            throw new ValidacaoException("Não tem agenda com esse Tipo de Exame");
        }
        return consultas;
    }


    @Transactional
    public DtoDetalheAgenda atualizaStatus(UUID id, DtoStatus dto) {
        var agenda = agendaRepository.getReferenceById(id);
        if(agenda!=null){
            agenda.setStatusEvento(dto.statusEvento());
            agendaRepository.save(agenda);
            return new DtoDetalheAgenda(agenda);
        }
        return null;
       
    }
}
