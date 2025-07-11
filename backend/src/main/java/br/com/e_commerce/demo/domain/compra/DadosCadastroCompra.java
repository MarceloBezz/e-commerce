package br.com.e_commerce.demo.domain.compra;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCompra(
    @NotBlank Integer quantidade
) {
    
}
