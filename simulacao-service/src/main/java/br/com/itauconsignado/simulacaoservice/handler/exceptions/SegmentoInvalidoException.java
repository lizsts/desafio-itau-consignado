package br.com.itauconsignado.simulacaoservice.handler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SegmentoInvalidoException extends Exception {

    public SegmentoInvalidoException(String message) {
        super(message);
    }
}

