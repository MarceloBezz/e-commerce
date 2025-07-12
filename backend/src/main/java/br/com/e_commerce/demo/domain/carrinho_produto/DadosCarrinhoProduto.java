package br.com.e_commerce.demo.domain.carrinho_produto;

public record DadosCarrinhoProduto(
    String nome,
    String descricao,
    Integer quantidade,
    Double preco,
    String anunciante
) {
    
}
