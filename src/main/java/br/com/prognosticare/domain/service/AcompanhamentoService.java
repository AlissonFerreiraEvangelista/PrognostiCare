package br.com.prognosticare.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


import br.com.prognosticare.domain.entity.acompanhamento.DtoAtualizaAcompanhamento;
import br.com.prognosticare.domain.entity.acompanhamento.DtoCadastroAcompanhamento;
import br.com.prognosticare.domain.entity.acompanhamento.DtoDetalheAcompanhamento;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;

import br.com.prognosticare.domain.entity.acompanhamento.AcompanhamentoEntity;
import br.com.prognosticare.domain.repository.AcompanhamentoRepository;
import br.com.prognosticare.infra.exception.ValidacaoException;

@Service
public class AcompanhamentoService {

    @Autowired
    AcompanhamentoRepository acompanhamentoRepository;

    String tokenFCM;

    public void checkMedications() {
        LocalDateTime now = LocalDateTime.now();

        var acompanhamentos = findAll();

        for (AcompanhamentoEntity acompanhamentoEntity : acompanhamentos) {
            if(now.isAfter(acompanhamentoEntity.getDataAcompanhamento())){
                sendNotification(acompanhamentoEntity);
                acompanhamentoEntity.atualizaProxaMedicacao();
            }
        }
        
    }

    private void sendNotification(AcompanhamentoEntity acompanhamento) {
       
        Message message = Message.builder()
        .setNotification(Notification.builder()
            .setTitle("Lembrete de Madicação")
            .setBody("É hora de tomar " + acompanhamento.getMedicacao())
            .setImage("tokenFCM")
            .build()            
        )
        .setToken(tokenFCM)
        .build();

                

        try {
            FirebaseApp.initializeApp();
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }

    public List<AcompanhamentoEntity> findAll(){
        List<AcompanhamentoEntity> acompanhamentos = acompanhamentoRepository.findAll();
        
        if(acompanhamentos.isEmpty()){
            throw new ValidacaoException("Não tem nenhum acompanhamento!");
        }
        return acompanhamentos;
    }

    @Transactional
    public AcompanhamentoEntity save(AcompanhamentoEntity acompanhamento) {
        return acompanhamentoRepository.save(acompanhamento);
    }

    public AcompanhamentoEntity getReferenceById(DtoAtualizaAcompanhamento dto) {
        var acompanhamento = acompanhamentoRepository.getReferenceById(dto.id());
        if(acompanhamento == null){
            throw  new ValidacaoException("Não encontrado");
        }
        acompanhamento.atualizaInformacao(dto);
        return acompanhamentoRepository.saveAndFlush(acompanhamento);
    }
}
