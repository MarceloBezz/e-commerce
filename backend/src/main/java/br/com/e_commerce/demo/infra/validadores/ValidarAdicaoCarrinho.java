package br.com.e_commerce.demo.infra.validadores;

import br.com.e_commerce.demo.domain.carrinho.Carrinho;
import br.com.e_commerce.demo.domain.produto.Produto;

public interface ValidarAdicaoCarrinho extends ValidarProdutoCompra{
    void produtoJaAdicionado(Produto produto, Carrinho carrinho);
}
