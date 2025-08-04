package br.com.e_commerce.demo.infra.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.e_commerce.demo.domain.carrinho.Carrinho;
import br.com.e_commerce.demo.domain.produto.Produto;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.infra.exception.RegraDeNegocioException;

@Component
public class ValidarAdicaoCarrinhoImpl implements ValidarAdicaoCarrinho {

    @Autowired
    private ValidarProdutoCompra validadorBase;

    @Override
    public void validarEstoqueDisponivel(Produto produto, int quantidade) {
        validadorBase.validarEstoqueDisponivel(produto, quantidade);
    }

    @Override
    public void validarComprador(Produto produto, Usuario usuario) {
        validadorBase.validarComprador(produto, usuario);
    }

    @Override
    public void produtoJaAdicionado(Produto produto, Carrinho carrinho) {
        var jaAdicionado = carrinho
                .getProdutos()
                .stream()
                .anyMatch(x -> x.getProduto().equals(produto));

        if (jaAdicionado) 
            throw new RegraDeNegocioException("Produto já adicionado no carrinho!");
    }
}
