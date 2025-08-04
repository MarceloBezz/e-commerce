package br.com.e_commerce.demo.infra.exception;

public class RegraDeNegocioException extends RuntimeException{
    public RegraDeNegocioException(String message) {
        super(message);
    }
}
