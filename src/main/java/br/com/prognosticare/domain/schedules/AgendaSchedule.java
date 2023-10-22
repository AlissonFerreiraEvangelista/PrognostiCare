package br.com.prognosticare.domain.schedules;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import br.com.prognosticare.domain.entity.agenda.AgendaEntity;
import br.com.prognosticare.domain.enums.Status;
import br.com.prognosticare.domain.service.AgendaService;
import br.com.prognosticare.infra.exception.ValidacaoException;

@Component
public class AgendaSchedule {

    @Autowired
    AgendaService aService;

    @Autowired
    FirebaseMessaging firebaseMessaging;


    @Scheduled(fixedRate = 60000)
    public void checkAgendamentos(){
       
        LocalDateTime now = LocalDateTime.now();
        
        var agendamentos = aService.findAllByStatusEventoAberto();

        for(AgendaEntity agendaEntity : agendamentos){
            LocalDateTime dataAgenda = agendaEntity.getDataAgenda();
            int intervaloData = agendaEntity.getIntervaloData();
            LocalDateTime dataNotificacao = dataAgenda.minusDays(intervaloData);

            if(agendaEntity.getStatusEvento() == Status.ABERTO
                && dataNotificacao.toLocalDate().isEqual(now.toLocalDate()) 
                && Boolean.TRUE.equals(agendaEntity.getNotificacao()) ){

                sendNotification(agendaEntity);
                agendaEntity.atualizaNotificacao();
                aService.save(agendaEntity);
            }

        }

    }

    private void sendNotification(AgendaEntity agendaEntity) {

        String tokenFCM = agendaEntity.getPessoa().getTokenFCM();

        if(tokenFCM != null && !tokenFCM.isEmpty()){

            Message message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle("Lembrete de Compromisso!")
                            .setBody("Sua Consulta é: " + agendaEntity.getDataAgenda())
                            .build())
                    .setToken(tokenFCM)
                    .build();
            try {
                firebaseMessaging.send(message);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }
        }else{
            throw new ValidacaoException("Erro no AgendaSchedule");
        }

    }
    
}
