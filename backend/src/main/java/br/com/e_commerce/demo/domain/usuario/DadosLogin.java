package br.com.e_commerce.demo.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
    @NotBlank
    String email,

    @NotBlank
    String senha) {
    
}
