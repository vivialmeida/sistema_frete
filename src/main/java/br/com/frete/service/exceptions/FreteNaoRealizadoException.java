package br.com.frete.service.exceptions;

public class FreteNaoRealizadoException extends RuntimeException {

    public FreteNaoRealizadoException(String mensagem) {
        super(mensagem);
    }
}
