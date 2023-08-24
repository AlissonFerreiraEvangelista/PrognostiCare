package br.com.prognosticare.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import br.com.prognosticare.domain.entity.email.EmailModel;
import br.com.prognosticare.domain.entity.email.StatusEmail;
import br.com.prognosticare.domain.entity.usuario.Usuario;
import br.com.prognosticare.domain.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    String email;

    String ownerRef = "Suporte";

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);
            emailModel.setStatusEmail(StatusEmail.SENT);

        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }

    }

    public void enviarEmailRecuperacaoSenha(Usuario usuario, String senhaDefault) {

        String assunto = "Redefinição de Senha";
        String corpo = "Segue a senha padrão para acessar o sistema:\n" + senhaDefault;
        EmailModel emailModel = new EmailModel();
        try {
            emailModel.setEmailFrom(email);
            emailModel.setOwnerRef(ownerRef);
            emailModel.setEmailTo(usuario.getEmail());
            emailModel.setSubject(assunto);
            emailModel.setText(corpo);
            emailModel.setSendDateEmail(LocalDateTime.now());
            sendEmail(emailModel);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }finally{
            emailRepository.save(emailModel);
        }

    }
}
