package br.com.prognosticare.web.controller;


import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prognosticare.domain.entity.email.EmailDto;
import br.com.prognosticare.domain.entity.email.EmailModel;
import br.com.prognosticare.domain.service.EmailService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/email")
public class EmailController {

    final
    EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sending-email")
    @ApiResponse(description = "Teste De Envio de Email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto,emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);

    }
}
