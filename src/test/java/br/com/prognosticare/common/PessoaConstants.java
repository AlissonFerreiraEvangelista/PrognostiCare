package br.com.prognosticare.common;

import br.com.prognosticare.domain.entity.pessoa.PessoaEntity;



import java.time.LocalDate;
import java.util.UUID;

public class PessoaConstants {

    
    public static final UUID ID = UUID.fromString("9f360616-1811-4621-9b2c-6c7af609ff04");
    public static final PessoaEntity PESSOA = new PessoaEntity("Alisson", "123-123-123-12", LocalDate.now(), "alisson.22559@gmail.com", "1234");
    public static final PessoaEntity INVALID_PESSOA = new PessoaEntity("", "", LocalDate.now(), "alisson.22559@gmail.com", "1234");

}
