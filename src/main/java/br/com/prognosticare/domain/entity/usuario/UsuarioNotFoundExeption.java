package br.com.prognosticare.domain.entity.usuario;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundExeption extends RuntimeException {
    public UsuarioNotFoundExeption() {
		super("Usuário não encontrado.");
	}

	public UsuarioNotFoundExeption(String message) {
		super(message);
	}
}
