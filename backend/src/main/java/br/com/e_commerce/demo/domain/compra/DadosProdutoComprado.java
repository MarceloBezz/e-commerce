package br.com.e_commerce.demo.domain.compra;

import br.com.e_commerce.demo.domain.produto.Categoria;
import br.com.e_commerce.demo.domain.produto.Produto;

public record DadosProdutoComprado(
        String nome,
        String descricao,
        Integer quantidade,
        Double preco,
        Categoria categoria) {
    public DadosProdutoComprado(Produto produto, int quantidade) {
        this(produto.getNome(), produto.getDescricao(), quantidade, produto.getPreco(), produto.getCategoria());
    }
}
