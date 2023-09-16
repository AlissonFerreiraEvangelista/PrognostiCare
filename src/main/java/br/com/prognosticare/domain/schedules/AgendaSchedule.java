package br.com.prognosticare.domain.schedules;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.prognosticare.domain.entity.agenda.AgendaEntity;
import br.com.prognosticare.domain.enums.Status;
import br.com.prognosticare.domain.service.AgendaService;

@Component
public class AgendaSchedule {

    @Autowired
    AgendaService aService;


    @Scheduled(fixedRate = 60000)
    public void checkAgendamentos(){
       
        LocalDateTime now = LocalDateTime.now();
        
        var agendamentos = aService.findAllByStatusEventoAberto();

        for(AgendaEntity agendaEntity : agendamentos){
            LocalDateTime dataAgenda = agendaEntity.getDataAgenda();
            int intervaloData = agendaEntity.getIntervaloData();
            LocalDateTime dataNotificacao = dataAgenda.minusDays(intervaloData);

            if(agendaEntity.getStatusEvento() == Status.ABERTO && dataNotificacao.toLocalDate().isEqual(now.toLocalDate()) && agendaEntity.getNotificacao() == false){
                System.out.println("Agendamento Marcado para: " + agendaEntity.getDataAgenda());
                agendaEntity.atualizaAgenda();
                aService.save(agendaEntity);
            }

        }

    }
    
}
