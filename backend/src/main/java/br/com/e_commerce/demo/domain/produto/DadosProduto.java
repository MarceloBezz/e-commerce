package br.com.e_commerce.demo.domain.produto;

public record DadosProduto(
    String nome,
    String descricao,
    int quantidade,
    double preco
) {
    public DadosProduto(Produto produto) {
        this(produto.getNome(), produto.getDescricao(), produto.getQuantidade(), produto.getPreco());
    }
}
