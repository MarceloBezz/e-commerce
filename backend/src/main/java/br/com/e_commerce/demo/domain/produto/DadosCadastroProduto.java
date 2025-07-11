package br.com.e_commerce.demo.domain.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DadosCadastroProduto(
        @NotBlank String nome,

        @NotBlank String descricao,

        @Positive Double preco,

        @Positive Integer quantidade) {

}
