package br.com.prognosticare.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.prognosticare.domain.entity.email.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long>{
    
}
