package br.com.e_commerce.demo.infra.validadores;

import org.springframework.stereotype.Component;

import br.com.e_commerce.demo.domain.compra.Compra;
import br.com.e_commerce.demo.domain.produto.Produto;

@Component
public class ValidarEstoqueProduto implements ValidadorCompra {

    @Override
    public void validar(Produto produto, Compra compra) throws Exception {
        for (var produtoCompra : compra.getProdutos()) {
            if (produtoCompra.getQuantidade() > produtoCompra.getProduto().getQuantidade()) {
                throw new Exception("O número de produtos solicitados excede o atual estoque do produto!");
            }
        }
    }

}
