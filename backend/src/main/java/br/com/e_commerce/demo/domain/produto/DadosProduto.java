package br.com.e_commerce.demo.domain.produto;

public record DadosProduto(
    String nome,
    String descricao,
    Integer quantidade,
    Double preco
) {
    public DadosProduto(Produto produto) {
        this(produto.getNome(), produto.getDescricao(), produto.getQuantidade(), produto.getPreco());
    }
}
